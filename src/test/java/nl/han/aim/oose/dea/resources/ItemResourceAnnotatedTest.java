package nl.han.aim.oose.dea.resources;

import nl.han.aim.oose.dea.services.ItemService;
import nl.han.aim.oose.dea.services.dto.ItemDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemResourceAnnotatedTest {
    @InjectMocks
    ItemResource itemResource;

    @Mock
    ItemService itemService;

    @Test
    void getJsonItems_whenCalled_ShouldReturnThreeItems() {
        // Arrange
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
