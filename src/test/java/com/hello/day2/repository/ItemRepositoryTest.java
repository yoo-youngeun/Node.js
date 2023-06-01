package com.hello.day2.repository;

import com.hello.day2.Day2ApplicationTests;
import com.hello.day2.enumclass.ItemStatus;
import com.hello.day2.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = Item.builder()
            .name("galaxy book")
            .status(ItemStatus.WAITING)
            .title("갤럭시북 pro 3")
            .content("zz")
            .price(2500000L)
            .regDate(LocalDateTime.now())
            .createBy("admin")
            .build();

        Item createItem = itemRepository.save(item);
    }

    @Test
    public void update() {
        Optional<Item> findItem = itemRepository.findItemsById(1L);

        findItem.ifPresent(
            selectItem -> {
                selectItem.setContent("dddddd");
                selectItem.setPrice(3500000L);
                selectItem.setUpdateDate(LocalDateTime.now());
                itemRepository.save(selectItem);
            }
        );
    }

    @Test
    public void delete() {
        Optional<Item> findItem = itemRepository.findItemsById(3L);

        findItem.ifPresent(
                deleteItem -> itemRepository.delete(deleteItem)
        );
    }
}
