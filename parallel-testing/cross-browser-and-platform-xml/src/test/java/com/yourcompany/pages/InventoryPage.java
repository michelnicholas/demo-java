package com.yourcompany.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePO {
    InventoryPage() {}

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(className = "inventory_container")
    private WebElement inventoryContainer;

    @FindBy(className = "inventory_list")
    private WebElement inventoryList;

    @FindBy(className = "header_secondary_container")
    private WebElement inventoryHeader;


    @FindBy(className = "product_label")
    private WebElement productTitle;

    /**
     * Returns the Div containing the item specified (zero-indexed)
     * @param itemNumber
     * @return
     */
    private WebElement getItemNumber(int itemNumber) {
        return inventoryList.findElement(By.cssSelector(String.format("div.inventory_item:nth-of-type(%d)", itemNumber)));
    }

    public String getItemName(int itemNumber) {
        WebElement itemName = getItemNumber(itemNumber).findElement(By.className("inventory_item_name"));
        return itemName.getText();
    }

    public String getItemDescription(int itemNumber) {
        WebElement itemDesc = getItemNumber(itemNumber).findElement(By.className("inventory_item_desc"));
        return itemDesc.getText();
    }

    public String getItemPrice(int itemNumber) {
        WebElement itemPrice = getItemNumber(itemNumber).findElement(By.className("inventory_item_price"));
        return itemPrice.getText();
    }

    public void addItemToCart(int itemNumber) {
        WebElement addToCart = getItemNumber(itemNumber).findElement(By.className("btn_primary"));
        addToCart.click();
    }

    public boolean isOnPage() {
        //return inventoryHeader.isDisplayed();
        return productTitle.isDisplayed();
    }

    public boolean itemAddedToCart(int itemNumber) {
        return getItemNumber(itemNumber).findElement(By.className("btn_secondary")).isDisplayed();
    }
}
