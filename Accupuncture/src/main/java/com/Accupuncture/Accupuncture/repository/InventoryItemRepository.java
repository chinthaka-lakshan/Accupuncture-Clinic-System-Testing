//---InventoryItemRepository

package com.Accupuncture.Accupuncture.repository;

import com.Accupuncture.Accupuncture.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
}
