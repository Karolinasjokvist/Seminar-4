package se.kth.iv1350.seminar4.integration;

import java.util.ArrayList;

import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;



/*
* External inventory system, with all inventory data
*/
public class EIShandler {
    private ArrayList<ItemDTO> itemsInEIS = new ArrayList<ItemDTO>();
    
    public EIShandler(){
        itemsInEIS.add(new ItemDTO("apple", "green apple", 3, 0.25, "1234"));
        itemsInEIS.add(new ItemDTO("chocolate bar", "marabou", 10, 0.3, "1235"));
        itemsInEIS.add(new ItemDTO("grill", "elgrill", 200, 0.3, "1236"));
        itemsInEIS.add(new ItemDTO(null, null, 0, 0, "ServerDownTest"));
    }

    /** 
    * Finds the item in the inventory system, by searching for the identifier
    * @param payment The payment that was made in the sale
    * @return ItemDTO the item that matches the identifier
    * @throws ItemNotFoundException Exception when an item is not found
    * @throws ServerDownException Exception when the server is down
    */
    public ItemDTO findItem(String identifier) throws ItemNotFoundException, ServerDownException{
        if(identifier.equals("ServerDownTest")) 
            throw new ServerDownException("The server is down");

        for(ItemDTO item : itemsInEIS) {
            if(identifier.equals(item.getIdentifier())){
                ItemDTO itemInfo = new ItemDTO(item.getName(), item.getDescription(), item.getPrice(), item.getVAT(), identifier);
                return itemInfo;
        }
        }

        throw new ItemNotFoundException("No item was found");
    }

    /** 
    * This updates the external inventory system 
    * @param saleDTO The sale containing items and date 
    */
    public void updateInventory(SaleDTO saleDTO){
        
    }
}
