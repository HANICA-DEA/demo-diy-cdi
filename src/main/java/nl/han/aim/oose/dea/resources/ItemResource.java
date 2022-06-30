package nl.han.aim.oose.dea.resources;

import nl.han.aim.oose.dea.annotations.DiyGET;
import nl.han.aim.oose.dea.annotations.DiyInject;
import nl.han.aim.oose.dea.annotations.DiyPath;
import nl.han.aim.oose.dea.services.ItemService;
import nl.han.aim.oose.dea.services.dto.ItemDTO;

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
