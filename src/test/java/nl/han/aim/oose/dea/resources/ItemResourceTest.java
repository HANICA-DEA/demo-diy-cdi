package nl.han.aim.oose.dea.resources;

import nl.han.aim.oose.dea.services.ItemService;
import nl.han.aim.oose.dea.services.dto.ItemDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemResourceTest {

    @Test
    void getJsonItems_whenCalled_ShouldReturnThreeItems() {
        // Arrange
        ItemResource itemResource = new ItemResource();
        ItemService itemService = mock(ItemService.class);
        List<ItemDTO> products = new ArrayList<>();
        products.add(new ItemDTO(0, "", new String[]{}, ""));
        products.add(new ItemDTO(1, "", new String[]{}, ""));
        products.add(new ItemDTO(2, "", new String[]{}, ""));
        when(itemService.getAll()).thenReturn(products);

        // Act
        itemResource.setItemService(itemService);

        // Assert
        assertEquals(3, itemResource.getJsonItems().size());
    }
}
