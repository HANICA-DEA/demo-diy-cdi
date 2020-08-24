package nl.han.ica.oose.dea.resources;

import nl.han.ica.oose.dea.annotations.DiyGET;
import nl.han.ica.oose.dea.annotations.DiyInject;
import nl.han.ica.oose.dea.annotations.DiyPath;
import nl.han.ica.oose.dea.services.ItemService;
import nl.han.ica.oose.dea.services.dto.ItemDTO;

import java.util.List;

@DiyPath("/items")
public class ItemResource {

    private ItemService itemService;

    @DiyGET
    public List<ItemDTO> getJsonItems() {
        return itemService.getAll();
    }

    @DiyInject
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
