package test.resources;

import ASTMiner.GumTreeUtil;
import com.github.gumtreediff.tree.ITree;
import diffapplier.ASTTest;
import diffapplier_pkg.ApplierUtil;
import diffapplier_pkg.Unparser;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import java.io.IOException;

/**
 */
public class TestPrettyPrint extends ASTTest {

    private String testFilePath = DATAPATH + "src/testdata/prettyprint/" +
            "PrettyPrintTestFile.java";

    @Test
    public void testSemanticsPreserved() {

        String testFileContents = this.getFileContents();

        /* Parse the test file into a Gumtree ITree */
        ITree originalTree = GumTreeUtil.getTreeFromDiff(testFileContents);

        String unparsedFromTree = Unparser.unparse(originalTree);

        /* Re-parse the unparsed ITree to make sure it means the same thing */
        ITree reparsedTree = GumTreeUtil.getTreeFromDiff(unparsedFromTree);

        Assert.assertTrue(reparsedTree.isClone(originalTree));
    }

    @Test
    public void testFormattingPreserved() {

        String testFileContents = this.getFileContents();

        /* Parse the test file into a Gumtree ITree */
        ITree originalTree = GumTreeUtil.getTreeFromDiff(testFileContents);

        String unparsedFromTree = Unparser.unparse(originalTree);

        /* Make sure the string formatting is preserved */
        Assert.assertEquals(testFileContents, unparsedFromTree);
    }

    @Nullable
    private String getFileContents() {
        String testFileContents = null;
        try {
            testFileContents = ApplierUtil.readWholeFile(testFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testFileContents;
    }

}
