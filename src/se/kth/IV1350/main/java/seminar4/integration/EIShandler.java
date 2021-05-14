package seminar3.integration;

import java.util.ArrayList;

import seminar3.dto.ItemDTO;
import seminar3.dto.SaleDTO;

/*
* EXternal inventory system, with all inventory data
*/
public class EIShandler {
    private ArrayList<ItemDTO> itemsInEIS = new ArrayList<ItemDTO>();
    
    public EIShandler(){
        itemsInEIS.add(new ItemDTO("apple", "green apple", 3, 0.25, "1234"));
        itemsInEIS.add(new ItemDTO("chocolate bar", "description", 10, 0.3, "1235"));
        itemsInEIS.add(new ItemDTO("noItem", "description", 0, 0, "null"));
    }

    /** 
    * Finds the item in the inventory system, y searching for the identifier
    * @param payment The payment that was made in the sale
    * @return ItemDTO the item that matches the identifier
    */
    public ItemDTO findItem(String identifier){
        for(ItemDTO item : itemsInEIS) {
            if(identifier.equals(item.getIdentifier())){
                ItemDTO itemInfo = new ItemDTO(item.getName(), item.getDescription(), item.getPrice(), item.getVAT(), identifier);
                return itemInfo;
        }
        }
        return itemsInEIS.get(2);
    }

    /** 
    * This updates the external inventory system 
    * @param saleDTO The sale containing items and date 
    */
    public void updateInventory(SaleDTO saleDTO){
        
    }
}
