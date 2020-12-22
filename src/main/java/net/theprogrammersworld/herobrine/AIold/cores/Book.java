package net.theprogrammersworld.herobrine.AIold.cores;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import net.theprogrammersworld.herobrine.HerobrineOld;
import net.theprogrammersworld.herobrine.Utils;
import net.theprogrammersworld.herobrine.AIold.Core;
import net.theprogrammersworld.herobrine.AIold.CoreResult;

public class Book extends Core {

	public Book() {
		super(CoreType.BOOK, AppearType.NORMAL, HerobrineOld.getPluginCore());
	}

	public CoreResult CallCore(Object[] data) {
		Player player = (Player) data[0];

		if (HerobrineOld.getPluginCore().getConfigDB().useWorlds.contains(player.getLocation().getWorld().getName())) {
			
			if (HerobrineOld.getPluginCore().getConfigDB().WriteBooks == true
				&& HerobrineOld.getPluginCore().getSupport().checkBooks(player.getLocation())) {
				
				int chance = Utils.getRandomGen().nextInt(100);
				if (chance > (100 - HerobrineOld.getPluginCore().getConfigDB().BookChance)) {
					Inventory chest = (Inventory) data[1];
					if (chest.firstEmpty() != -1) {
						if (HerobrineOld.getPluginCore().getAICore().getResetLimits().isBook()) {
							chest.setItem(chest.firstEmpty(), newBook());
							return new CoreResult(true, "Herobrine's book created.");
						}
					} else {
						return new CoreResult(false, "Herobrine's book creation failed.");
					}
				} else {
					return new CoreResult(false, "Herobrine's books are prohibited.");
				}
			} else {
				return new CoreResult(false, player.getDisplayName() + " is in a world that Herobrine is not allowed in.");
			}
		}
		return new CoreResult(false, "Herobrine's book creation failed.");
	}

	public ItemStack newBook() {

		int count = HerobrineOld.getPluginCore().getConfigDB().useBookMessages.size();

		int chance = Utils.getRandomGen().nextInt(count);

		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();

		ArrayList<String> list = new ArrayList<String>();

		meta.setTitle("");
		meta.setAuthor("");

		list.add(0, (String) HerobrineOld.getPluginCore().getConfigDB().useBookMessages.get(chance));

		meta.setPages(list);

		book.setItemMeta(meta);

		return (ItemStack) book;
	}

}
