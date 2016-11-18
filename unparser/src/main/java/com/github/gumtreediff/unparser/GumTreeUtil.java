package com.github.gumtreediff.unparser;

import com.github.gumtreediff.actions.ActionGenerator;
import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.gen.jdt.JdtTreeGenerator;
import com.github.gumtreediff.matchers.Matcher;
import com.github.gumtreediff.matchers.Matchers;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.Tree;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by @author @pillravi on 11/17/16.
 *
 * Originally part of ASTMiner by @cdmihai and @MichaelHilton
 */
public class GumTreeUtil {

    // New function that will calculate AST diffs via GumTree
    public static List<Action> findASTDiffs(ITree src, ITree dst) {
        List<Action> actions = null;
        try {
            Matcher m = Matchers.getInstance().getMatcher(src, dst); // retrieve the default matcher
            m.match();
            ActionGenerator g = new ActionGenerator(src, dst, m.getMappings());
            g.generate();
            actions = g.getActions(); // return the actions
        } catch (UnsupportedOperationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return actions;
    }

    public static ITree getTreeFromDiff(String fileContents){
        fileContents = fileContents.replace("\r\n","\n");
        ITree src = null;
        try {
            src = new JdtTreeGenerator().generateFromString(fileContents).getRoot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return src;
    }


    public static String toJSON(ITree t) {
        StringWriter s = new StringWriter();
        String result = null;
        try {
            JsonWriter w = new JsonWriter(s);
            w.setIndent("\t");
            writeJSONTree(t, w);
            w.close();

            result = s.toString();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void writeJSONTree(ITree t, JsonWriter w) throws IOException {
        w.beginObject();

        w.name("type").value(Integer.toString(t.getType()));

        w.name("id").value(Integer.toString(t.getId()));

        if (!Tree.NO_LABEL.equals(t.getLabel())) w.name("label").value(t.getLabel());

        if (Tree.NO_VALUE != t.getPos()) {
            w.name("pos").value(Integer.toString(t.getPos()));
            w.name("length").value(Integer.toString(t.getLength()));
        }
        w.name("children");
        w.beginArray();
        for (ITree c : t.getChildren())
            writeJSONTree(c, w);
        w.endArray();

        w.endObject();
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    /**
     * Convert a millisecond duration to a string format
     *
     * @param millis A duration to convert to a string form
     * @return A string of the form "X Days Y Hours Z Minutes A Seconds".
     */
    public static String getDurationBreakdown(long millis)
    {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hours ");
        sb.append(minutes);
        sb.append(" Minutes ");
        sb.append(seconds);
        sb.append(" Seconds");

        return(sb.toString());
    }
}
