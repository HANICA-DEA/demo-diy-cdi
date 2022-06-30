package nl.han.aim.oose.dea.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {
    @Test
    public void getAll_whenCalled_ReturnsThreeItems() {
        ItemService itemService = new ItemService();
        assertEquals(3, itemService.getAll().size());
    }
}
