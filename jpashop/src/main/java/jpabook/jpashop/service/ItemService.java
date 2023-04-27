package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    // saveItem()
    @Transactional  // save 할 거면 어노테이션 별도로 박아주기. 클래스에 readonly로 박아뒀으니까. 모든 public에 디폴트로 깔림
    public void save(Item item){
        itemRepository.save(item);
    }
    // findItems()
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    // findOne()
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
