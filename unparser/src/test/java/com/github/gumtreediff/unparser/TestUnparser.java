package com.github.gumtreediff.unparser;

import com.github.gumtreediff.tree.ITree;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by @author @pillravi on 11/17/16.
 *
 * Originally created on 3/7/16 by @pillravi as part of PANDA.
 *
 * Tests whether GumTree's Tree class can be parsed and unparsed.
 * 1) Tests for maintained semantics.
 * 2) Also has tests for maintained formatting, but these are mainly to help
 *    the programmer look at the diff and figure out why semantics aren't
 *    maintained if the corresponding semantics test fails. For now, they are
 *    ignored after the corresponding semantics test passes.
 *
 * Note: "SP" stands for "Semantics Preserved"
 *       "FP" = "Formatting Preserved"
 *       "JP" = "JSON Preserved"
 */
public class TestUnparser {
    private String userDir = System.getProperty("user.dir") + "/";
    private String resources = userDir + "src/test/resources/";

    private String generalFile = resources + "PrettyPrintTestFile.java";
    private String annotationFile = resources + "Annotations.java";
    private String beforeMoveStmtFile = resources + "MoveStatementBefore.java";
    private String afterMoveStmtFile = resources + "MoveStatementAfter.java";
    private String moveStmtNoAssn = resources + "MoveStmtNoAssn.java";
    private String toCollectionBefore = resources + "ToCollectionBefore.java";
    private String toCollectionAfter = resources + "ToCollectionAfter.java";
    private String methodOnNewObj = resources + "MethodInvokedOnNewObject.java";

    @Test
    public void useJava8Nodes() {
        /* There are 92 instead of 83 */
        /* Remember the enum has a dummy node at 0 to make it 1-indexed */
        Assert.assertEquals(92, NodeTypeEnum.values().length - 1);
    }

    /* Test a general file */
    @Test
    public void testSPGeneral() {
        semanticsPreserved(generalFile);
    }

    @Ignore
    @Test
    public void testFPGeneral() {
        formattingPreserved(generalFile);
    }

    /* Test a file with annotations, as well as some more new nodes. */
    @Test
    public void testSPAnnotations() {
        semanticsPreserved(annotationFile);
    }

    @Ignore
    @Test
    public void testFPAnnotations() {
        formattingPreserved(annotationFile);
    }

    @Test
    public void testHSPAnnotations() {
        hashStringPreserved(annotationFile);
    }

    /* Test the files that will be used for a moving demo */
    @Test
    public void testSPBeforeMoveStatement() {
        semanticsPreserved(beforeMoveStmtFile);
    }

    @Ignore
    @Test
    public void testFPMoveStatement() {
        formattingPreserved(beforeMoveStmtFile);
    }

    @Ignore
    @Test
    public void testJPMoveStatement() {
        JSONPreserved(beforeMoveStmtFile);
    }

    @Test
    public void testSPAfterMoveStatement() {
        semanticsPreserved(afterMoveStmtFile);
    }

    @Ignore
    @Test
    public void testFPMoveNoAssn() {
        formattingPreserved(moveStmtNoAssn);
    }

    @Ignore
    @Test
    public void testJPMoveNoAssn() {
        JSONPreserved(moveStmtNoAssn);
    }

    @Test
    public void testSPMoveNoAssn() {
        semanticsPreserved(moveStmtNoAssn);
    }

    /* Test the files that will be used for a Convert Element to Collection
     * demo */
    @Test
    public void testSPToCollectionBefore() {
        semanticsPreserved(toCollectionBefore);
    }

    @Ignore
    @Test
    public void testFPToCollectionAfter() {
        formattingPreserved(toCollectionAfter);
    }

    @Ignore
    @Test
    public void testJPToCollectionAfter() {
        JSONPreserved(toCollectionAfter);
    }

    @Test
    public void testSPToCollectionAfter() {
        semanticsPreserved(toCollectionAfter);
    }

    /* Test @jrfaller's question: are there problems printing, e.g.,
     * (new Bar()).toString(); */
    @Test
    public void testSPMethodInvokedOnNewObj() {
        semanticsPreserved(methodOnNewObj);
    }

    @Ignore
    @Test
    public void testFPMethodInvokedOnNewObj() {
        formattingPreserved(methodOnNewObj);
    }

    @Ignore
    @Test
    public void testJPMethodInvokedOnNewObj() {
        JSONPreserved(methodOnNewObj);
    }

    /* Methods that are used to test many files:
     * JSONPreserved: useful for getting what the tree should look like, but
     *     can mostly be ignored after that
     * hashStringPreserved: useful for finding JavaDocs and other strange things
     *     that may confuse you - use a fine-grained char-by-char diff on these
     * semanticsPreserved: the true test of unparsing functionality.
     * formattingPreserved: the easiest way for a human to tell if the tree is
     *     being unparsed correctly, but can be ignored after semanticsPreserved
     *     passes. */
    private void JSONPreserved(String path) {
        /* This method is actually superfluous from a functionality-testing
         * standpoint, since if the semantics are preserved, the JSON will be
         * preserved. But this method is useful for getting the expected JSON
         * to look at while implementing the Unparser class. */
        String testFileContents = getFileContents(path);

        /* Parse the test file into a Gumtree ITree */
        ITree originalTree = GumTreeUtil.getTreeFromDiff(testFileContents);

        String unparsedFromTree = Unparser.unparse(originalTree);

        /* Re-parse the unparsed ITree to make sure it means the same thing */
        ITree reparsedTree = GumTreeUtil.getTreeFromDiff(unparsedFromTree);

        /* Get the JSON for both trees */
        String originalJSON = GumTreeUtil.toJSON(originalTree);
        String actualJSON = GumTreeUtil.toJSON(reparsedTree);

        Assert.assertEquals(originalJSON, actualJSON);
    }

    private void hashStringPreserved(String path) {
        String testFileContents = getFileContents(path);

        /* Parse the test file into a Gumtree ITree */
        ITree originalTree = GumTreeUtil.getTreeFromDiff(testFileContents);

        String unparsedFromTree = Unparser.unparse(originalTree);

        /* Re-parse the unparsed ITree to make sure it means the same thing */
        ITree reparsedTree = GumTreeUtil.getTreeFromDiff(unparsedFromTree);

        /* Remember the order is 1) Expected, 2) Actual */
        Assert.assertEquals(originalTree.toStaticHashString(),
                reparsedTree.toStaticHashString());
    }

    private void semanticsPreserved(String path) {
        String testFileContents = getFileContents(path);

        /* Parse the test file into a Gumtree ITree */
        ITree originalTree = GumTreeUtil.getTreeFromDiff(testFileContents);

        String unparsedFromTree = Unparser.unparse(originalTree);

        /* Re-parse the unparsed ITree to make sure it means the same thing */
        ITree reparsedTree = GumTreeUtil.getTreeFromDiff(unparsedFromTree);

        Assert.assertTrue(reparsedTree.isClone(originalTree));
    }

    private void formattingPreserved(String path) {
        String testFileContents = getFileContents(path);

        /* Parse the test file into a Gumtree ITree */
        ITree originalTree = GumTreeUtil.getTreeFromDiff(testFileContents);

        String unparsedFromTree = Unparser.unparse(originalTree);

        /* Make sure the string formatting is preserved */
        Assert.assertEquals(testFileContents, unparsedFromTree);
    }

    private String getFileContents(String path) {
        String testFileContents = null;
        try {
            testFileContents = ApplierUtil.readWholeFile(path);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return testFileContents;
    }

}
