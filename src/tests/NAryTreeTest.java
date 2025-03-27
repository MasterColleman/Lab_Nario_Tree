package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import structures.NAryTree;
import java.util.*;


class NAryTreeTest {
    private NAryTree<String> tree;

    @BeforeEach
    void setUp() {
        tree = new NAryTree<>("root", Comparator.naturalOrder());
        tree.add("root", "child1");
        tree.add("root", "child2");
        tree.add("child1", "child3");
        tree.add("child1", "child4");
        tree.add("child2", "child5");
        tree.add("child2", "child6");
        tree.add("child3", "child7");
        tree.add("child3", "child8");
        tree.add("child6", "child9");
    }

    @Test
    void testAddWithDeepHierarchy() {
        assertTrue(tree.add("child9", "child10"));
        assertNotNull(tree.search("child10"));
    }

    @Test
    void testAddDuplicate() {
        assertFalse(tree.add("child1", "child3"));
    }

    @Test
    void testRemoveLeafNode() {
        assertTrue(tree.remove("child3", "child8"));
        assertNull(tree.search("child8"));
    }

    @Test
    void testRemoveSubtree() {
        assertTrue(tree.remove("child1", "child3"));
        assertNull(tree.search("child3"));
        assertNull(tree.search("child7"));
        assertNull(tree.search("child8"));
    }

    @Test
    void testRemoveRoot() {
        Iterator<String> iterator = tree.iterator();
        iterator.next();
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }


    @Test
    void testIteratorOrder() {
        List<String> expected = Arrays.asList("root", "child1", "child3", "child7", "child8", "child4", "child2", "child5", "child6", "child9");
        List<String> actual = new ArrayList<>();
        Iterator<String> iterator = tree.iterator();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        assertEquals(expected, actual);
    }

    @Test
    void testIteratorRemove() {
        Iterator<String> iterator = tree.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertNull(tree.search("child1"));
        assertNull(tree.search("child3"));
        assertNull(tree.search("child7"));
    }
}
