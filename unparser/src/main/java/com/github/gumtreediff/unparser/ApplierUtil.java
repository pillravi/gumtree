package com.github.gumtreediff.unparser;

import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.actions.model.Delete;
import com.github.gumtreediff.actions.model.Move;
import com.github.gumtreediff.tree.ITree;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by @author @pillravi on 11/17/16.
 *
 * Originally created on 3/2/16 by @pillravi as part of PANDA.
 */
public final class ApplierUtil {

//    private ApplierUtil() {
//        /* private so no client code can instantiate this util class */
//    }

    public static ITree javaFileToITree(String fileName) {
        String fileContents = null;
        try {
            fileContents = readWholeFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GumTreeUtil.getTreeFromDiff(fileContents);
    }

    public static String readWholeFile(String filename) throws IOException {
        FileInputStream file = new FileInputStream(filename);
        Scanner scanner = new Scanner(file).useDelimiter("\\Z");
        String fileString;
        if (scanner.hasNext()) {
            fileString = scanner.next();
        } else {
            throw new IOException("No content in file");
        }
        return fileString;
    } /* end readWholeFile */

    public static List<Action> removeRedundantActions(List<Action> actions) {

        List<Action> paredList = new ArrayList<>();
        for (Action element : actions) {
            paredList.add(element);
        }

        for (int i = 0; i < actions.size(); i++) {
            Action curAction = actions.get(i);
            ITree curNode = curAction.getNode();
            int curNodeId = curNode.getId();
            String curOperation = curAction.toString().split(" ")[0];

            /* Don't move a node into another node if it's already there. */
            if (curOperation.equals("MOV")) {
                Move move = (Move) curAction;
                ITree destination = move.getParent();
                List<ITree> destinationDescendants = destination.getDescendants();
                for (ITree descendant : destinationDescendants) {
                    if (descendant.isClone(curNode)
                            && descendant.isSimilar(curNode)) {
                        paredList.remove(curAction);
                        paredList.add(new Delete(curNode));
                    }
                }
            }

            for (Action otherAction : actions) {

                ITree otherNode = otherAction.getNode();
                int otherNodeId = otherNode.getId();
                String otherOperation = otherAction.toString().split(" ")[0];

                Iterable<ITree> iterable = otherNode.preOrder();
                Iterator<ITree> iterator = iterable.iterator();

                if (curNodeId == otherNodeId /* Don't compare current action with itself! */
                        && curOperation.equals(otherOperation)) {
                    continue;
                }

                /* The two actions aren't redundant if their operations are different, so move on  */
                /* - Actually, they can be redundant. */
                if (!curOperation.equals(otherOperation)) {
                    continue;
                }


                while (iterator.hasNext()) {
                    ITree nextNode = iterator.next();
                    int nextNodeId = nextNode.getId();

                    if (curNodeId == nextNodeId) {
                        paredList.remove(curAction);
                        break;
                    }
                }
            }
        }

        List<List<Integer>> allIds = new ArrayList<>(paredList.size());
        for (Action a : paredList) {
            List<ITree> descendants = a.getNode().getDescendants();
            List<Integer> ids = new ArrayList<>();
            for (ITree d : descendants) {
                ids.add(d.getId());
            }
            allIds.add(ids);
        }
        return paredList;
    } /* end removeRedundantActions */

} /* end class ApplierUtil */
