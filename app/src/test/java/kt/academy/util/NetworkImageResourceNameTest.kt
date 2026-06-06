package kt.academy.util

import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkImageResourceNameTest {

    @Test
    fun `test resource name extraction`() {
        assertEquals("compose_background_1", extractResourceName("https://marcinmoskala.com/courses/polished_compose/resources/compose_background_1.png"))
        assertEquals("compose", extractResourceName("https://marcinmoskala.com/courses/compose/resources/compose.png"))
        assertEquals("logo", extractResourceName("https://marcinmoskala.com/courses/kotlin/advanced/resources/logo.png"))
        assertEquals(null, extractResourceName("https://marcinmoskala.com/other/resources/compose.png"))
        assertEquals(null, extractResourceName("https://marcinmoskala.com/courses/compose/compose.png"))
    }
}
