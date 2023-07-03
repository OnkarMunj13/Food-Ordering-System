package in.cdac.acts.utils;

import in.cdac.acts.domain.*;
import java.util.Comparator;

public class NameComparator implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		return item1.getName().compareToIgnoreCase(item2.getName());
	}

}
