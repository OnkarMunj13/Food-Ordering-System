package in.cdac.acts.utils;

import in.cdac.acts.domain.*;
import java.util.Comparator;

public class PriceComparator implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		return (int) (item1.getPrice() - item2.getPrice());
	}

}
