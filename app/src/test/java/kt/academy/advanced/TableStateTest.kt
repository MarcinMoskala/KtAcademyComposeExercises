package kt.academy.advanced

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests for [TableStateImpl], the implementation returned by [rememberTableState].
 *
 * Tests cover every method and property setter of the [TableState] interface.
 * Removing any behavioural line from [TableStateImpl] should cause at least one
 * test here to fail.
 */
class TableStateTest {
    private val selector: (String) -> Comparable<*> = { it }

    private fun tableState(vararg columnKeys: String): TableState<String> =
        TableState(
            initialData = emptyList(),
            initialColumns = columnKeys.associateWith { selector },
        )

    private fun tableStateWithData(
        data: List<String>,
        vararg columnKeys: String,
    ): TableState<String> =
        TableState(
            initialData = data,
            initialColumns = columnKeys.associateWith { selector },
        )

    // =========================================================================
    // Initial state
    // =========================================================================

    @Test
    fun `should have null sort column key initially`() {
        val state = tableState("Name", "Age")
        assertNull(state.sortColumnKey)
    }

    @Test
    fun `should be sort ascending initially`() {
        val state = tableState("Name", "Age")
        assertTrue(state.isSortAscending)
    }

    @Test
    fun `should have column order matching initial column keys`() {
        val state = tableState("Name", "Age", "City")
        assertEquals(listOf("Name", "Age", "City"), state.columnOrder)
    }

    @Test
    fun `should have empty column order when no columns provided`() {
        val state = tableState()
        assertTrue(state.columnOrder.isEmpty())
    }

    @Test
    fun `should initialize with provided data`() {
        val state = tableStateWithData(listOf("Alice", "Bob"), "Name")
        assertEquals(listOf("Alice", "Bob"), state.data)
    }

    // =========================================================================
    // toggleSort
    // =========================================================================

    @Test
    fun `should set sort column when toggling a new column`() {
        val state = tableState("Name")
        state.toggleSort("Name")
        assertEquals("Name", state.sortColumnKey)
    }

    @Test
    fun `should sort ascending when toggling a new column`() {
        val state = tableState("Name")
        state.toggleSort("Name")
        assertTrue(state.isSortAscending)
    }

    @Test
    fun `should flip to descending when toggling the current sort column`() {
        val state = tableState("Name")
        state.toggleSort("Name")  // ascending
        state.toggleSort("Name")  // toggle → descending
        assertFalse(state.isSortAscending)
    }

    @Test
    fun `should flip back to ascending when toggling a descending column again`() {
        val state = tableState("Name")
        state.toggleSort("Name")  // ascending
        state.toggleSort("Name")  // descending
        state.toggleSort("Name")  // ascending again
        assertTrue(state.isSortAscending)
    }

    @Test
    fun `should change sort column and reset ascending when toggling a different column`() {
        val state = tableState("Name", "Age")
        state.toggleSort("Name")
        state.toggleSort("Name")  // now descending by Name
        state.toggleSort("Age")   // switch to Age → must reset to ascending
        assertEquals("Age", state.sortColumnKey)
        assertTrue(state.isSortAscending)
    }

    @Test
    fun `should not change sort direction when toggling a new column after sorting`() {
        val state = tableState("Name", "Age")
        state.toggleSort("Name")
        state.toggleSort("Name")  // descending
        state.toggleSort("Age")   // new column → ascending, not descending
        assertTrue(state.isSortAscending)
    }

    // =========================================================================
    // setSort
    // =========================================================================

    @Test
    fun `should set sort column and ascending direction with setSort`() {
        val state = tableState("Name")
        state.setSort("Name", ascending = true)
        assertEquals("Name", state.sortColumnKey)
        assertTrue(state.isSortAscending)
    }

    @Test
    fun `should set sort column and descending direction with setSort`() {
        val state = tableState("Name")
        state.setSort("Name", ascending = false)
        assertEquals("Name", state.sortColumnKey)
        assertFalse(state.isSortAscending)
    }

    @Test
    fun `should clear sort column when setSort is called with null key`() {
        val state = tableState("Name")
        state.toggleSort("Name")
        state.setSort(null, ascending = true)
        assertNull(state.sortColumnKey)
    }

    @Test
    fun `should set ascending flag to false when setSort specifies descending`() {
        val state = tableState("Name")
        state.setSort("Name", ascending = false)
        assertFalse(state.isSortAscending)
    }

    @Test
    fun `should overwrite existing sort with setSort`() {
        val state = tableState("Name", "Age")
        state.setSort("Name", ascending = true)
        state.setSort("Age", ascending = false)
        assertEquals("Age", state.sortColumnKey)
        assertFalse(state.isSortAscending)
    }

    // =========================================================================
    // sortByColumn
    // =========================================================================

    @Test
    fun `should sort by column ascending by default`() {
        val state = tableState("Name")
        state.sortByColumn("Name")
        assertEquals("Name", state.sortColumnKey)
        assertTrue(state.isSortAscending)
    }

    @Test
    fun `should sort by column descending when ascending is false`() {
        val state = tableState("Name")
        state.sortByColumn("Name", ascending = false)
        assertEquals("Name", state.sortColumnKey)
        assertFalse(state.isSortAscending)
    }

    @Test
    fun `should sort by column ascending when ascending is true`() {
        val state = tableState("Name")
        state.sortByColumn("Name", ascending = false)  // descending first
        state.sortByColumn("Name", ascending = true)   // then back to ascending
        assertTrue(state.isSortAscending)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw when sorting by an unknown column key`() {
        val state = tableState("Name")
        state.sortByColumn("Unknown")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw when sorting by an empty column key`() {
        val state = tableState("Name")
        state.sortByColumn("")
    }

    // =========================================================================
    // clearSort
    // =========================================================================

    @Test
    fun `should set sort column to null after clearSort`() {
        val state = tableState("Name")
        state.toggleSort("Name")
        state.clearSort()
        assertNull(state.sortColumnKey)
    }

    @Test
    fun `should reset sort direction to ascending after clearSort`() {
        val state = tableState("Name")
        state.setSort("Name", ascending = false)
        state.clearSort()
        assertTrue(state.isSortAscending)
    }

    @Test
    fun `should remain cleared when clearSort is called on already unsorted state`() {
        val state = tableState("Name")
        state.clearSort()
        assertNull(state.sortColumnKey)
        assertTrue(state.isSortAscending)
    }

    // =========================================================================
    // moveColumn
    // =========================================================================

    @Test
    fun `should move column one step forward`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(0, 1)
        assertEquals(listOf("B", "A", "C"), state.columnOrder)
    }

    @Test
    fun `should move column one step backward`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(2, 1)
        assertEquals(listOf("A", "C", "B"), state.columnOrder)
    }

    @Test
    fun `should move column to last position`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(0, 2)
        assertEquals(listOf("B", "C", "A"), state.columnOrder)
    }

    @Test
    fun `should move column to first position`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(2, 0)
        assertEquals(listOf("C", "A", "B"), state.columnOrder)
    }

    @Test
    fun `should not change column order when moving to same index`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(1, 1)
        assertEquals(listOf("A", "B", "C"), state.columnOrder)
    }

    @Test
    fun `should not change column order when from index is out of bounds`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(5, 0)
        assertEquals(listOf("A", "B", "C"), state.columnOrder)
    }

    @Test
    fun `should not change column order when from index is negative`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(-1, 0)
        assertEquals(listOf("A", "B", "C"), state.columnOrder)
    }

    @Test
    fun `should clamp to index to last when target exceeds column count`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(0, 10)
        assertEquals(listOf("B", "C", "A"), state.columnOrder)
    }

    @Test
    fun `should clamp to index to zero when target is negative`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(2, -5)
        assertEquals(listOf("C", "A", "B"), state.columnOrder)
    }

    @Test
    fun `should support multiple sequential column moves`() {
        val state = tableState("A", "B", "C", "D")
        state.moveColumn(0, 1)  // [B, A, C, D]
        state.moveColumn(3, 0)  // [D, B, A, C]
        assertEquals(listOf("D", "B", "A", "C"), state.columnOrder)
    }

    // =========================================================================
    // data setter
    // =========================================================================

    @Test
    fun `should update data when data setter is called`() {
        val state = tableStateWithData(listOf("Alice"), "Name")
        state.data = listOf("Bob", "Carol")
        assertEquals(listOf("Bob", "Carol"), state.data)
    }

    @Test
    fun `should allow setting data to empty list`() {
        val state = tableStateWithData(listOf("Alice", "Bob"), "Name")
        state.data = emptyList()
        assertTrue(state.data.isEmpty())
    }

    @Test
    fun `should replace previous data completely`() {
        val state = tableStateWithData(listOf("Alice", "Bob"), "Name")
        state.data = listOf("Carol")
        assertEquals(1, state.data.size)
        assertEquals("Carol", state.data[0])
    }

    // =========================================================================
    // columns setter
    // =========================================================================

    @Test
    fun `should update columns map when setter is called`() {
        val initial = mapOf<String, (String) -> Comparable<*>>("A" to selector)
        val updated = mapOf<String, (String) -> Comparable<*>>("B" to selector)
        val state: TableState<String> = TableState(emptyList(), initial)
        state.columns = updated
        assertFalse("A" in state.columns)
        assertTrue("B" in state.columns)
    }

    @Test
    fun `should add new column key to end of column order`() {
        val state = tableState("A", "B")
        val newColumns = mapOf<String, (String) -> Comparable<*>>(
            "A" to selector, "B" to selector, "C" to selector,
        )
        state.columns = newColumns
        assertEquals(listOf("A", "B", "C"), state.columnOrder)
    }

    @Test
    fun `should remove column from column order when column is removed`() {
        val state = tableState("A", "B", "C")
        val newColumns = mapOf<String, (String) -> Comparable<*>>(
            "A" to selector, "C" to selector,
        )
        state.columns = newColumns
        assertEquals(listOf("A", "C"), state.columnOrder)
    }

    @Test
    fun `should retain existing column order when new columns are set`() {
        val state = tableState("A", "B", "C")
        state.moveColumn(0, 2)  // reorder to [B, C, A]
        val newColumns = mapOf<String, (String) -> Comparable<*>>(
            "A" to selector, "B" to selector, "C" to selector, "D" to selector,
        )
        state.columns = newColumns
        assertEquals(listOf("B", "C", "A", "D"), state.columnOrder)
    }

    @Test
    fun `should clear sorting if sorted column is removed from columns`() {
        val state = tableState("A", "B")
        state.toggleSort("A")
        val newColumns = mapOf<String, (String) -> Comparable<*>>("B" to selector)
        state.columns = newColumns
        assertNull(state.sortColumnKey)
    }

    @Test
    fun `should not clear sorting if sorted column is still in new columns`() {
        val state = tableState("A", "B")
        state.toggleSort("A")
        val newColumns = mapOf<String, (String) -> Comparable<*>>(
            "A" to selector, "C" to selector,
        )
        state.columns = newColumns
        assertEquals("A", state.sortColumnKey)
    }

    @Test
    fun `should not update state when same columns reference is assigned`() {
        val columns = mapOf<String, (String) -> Comparable<*>>(
            "A" to selector, "B" to selector,
        )
        val state: TableState<String> = TableState(emptyList(), columns)
        state.moveColumn(0, 1)  // reorder to [B, A]
        state.toggleSort("A")   // sort by A

        state.columns = columns // same reference → should be a no-op

        assertEquals(listOf("B", "A"), state.columnOrder) // order unchanged
        assertEquals("A", state.sortColumnKey)             // sort preserved
    }

    @Test
    fun `should not remove any column key when all columns are kept`() {
        val state = tableState("X", "Y", "Z")
        val newColumns = mapOf<String, (String) -> Comparable<*>>(
            "X" to selector, "Y" to selector, "Z" to selector,
        )
        state.columns = newColumns
        assertEquals(listOf("X", "Y", "Z"), state.columnOrder)
    }

    @Test
    fun `should handle replacing all columns with completely different keys`() {
        val state = tableState("A", "B")
        state.toggleSort("A")
        val newColumns = mapOf<String, (String) -> Comparable<*>>(
            "C" to selector, "D" to selector,
        )
        state.columns = newColumns
        assertEquals(listOf("C", "D"), state.columnOrder)
        assertNull(state.sortColumnKey)  // "A" was removed → sort cleared
    }
}
