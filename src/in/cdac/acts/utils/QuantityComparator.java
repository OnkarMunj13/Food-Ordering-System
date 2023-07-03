package in.cdac.acts.utils;

import in.cdac.acts.domain.*;
import java.util.Comparator;

public class QuantityComparator implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		return item1.getQuantity() - item2.getQuantity();
	}

}
