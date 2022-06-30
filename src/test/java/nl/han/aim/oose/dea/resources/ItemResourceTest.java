package nl.han.aim.oose.dea.resources;

import nl.han.aim.oose.dea.services.ItemService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemResourceTest {

    @Test
    void getJsonItems_whenCalled_ShouldReturnThreeItems() {
        ItemResource itemResource = new ItemResource();
        itemResource.setItemService(new ItemService());
        assertEquals(3, itemResource.getJsonItems().size());
    }
}
