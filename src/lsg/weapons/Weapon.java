package lsg.weapons;

import lsg.bags.Collectible;
import lsg.consumables.repair.RepairKit;
import lsg.exceptions.ConsumeNullException;

/**
 * Classe Weapon de type Collectible
 * @author jenni
 *
 */
public class Weapon implements Collectible {
	
	/**
	 * nom humanise de l'arme
	 */
	protected String name; 
	
	/**
	 * dommages minimum pouvant être causes
	 */
	protected int minDamage;
	
	/**
	 * dommages maximums pouvant être causes avec une precision de 100%
	 */
	protected int maxDamage; 
	
	/**
	 * cout en force pour frapper
	 */
	protected int stamCost; 
	
	/**
	 * nombre de coups pouvant etre portes avant que l'arme ne se casse
	 */
	protected int durability; 
	
	public static final String DURABILITY_STAT_STRING = "durability";
	protected static final String MIN_STAT_STRING = "min";
	protected static final String MAX_STAT_STRING = "max";
	protected static final String STAMINA_STAT_STRING = "stamina";
	
	/**
	 * cree une arme en lui donnant un nom, des dommages minimum, des dommages maximum, un cout en stamina et une durabilite
	 * @param name nom de l'arme
	 * @param minDamage minimum des degats pouvant etre causes
	 * @param maxDamage maximum des degats pouvant etre causes
	 * @param stamCost cout en stamina
	 * @param durability durabilite
	 */
	public Weapon(String name, int minDamage, int maxDamage, int stamCost, int durability){
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.stamCost = stamCost;
		this.durability = durability;
		
	}

	/**
	 * @return name nom de l'arme
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return minDamages minimum de dommages pouvant etre causes par l'arme
	 */
	public int getMinDamage() {
		return minDamage;
	}

	/**
	 * @return maxDamages maximum de dommages pouvant etre causes par l'arme
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * @return stamCost cout en stamina 
	 */
	public int getStamCost() {
		return stamCost;
	}

	/**
	 * @return durability durabilite de l'arme
	 */
	public int getDurability() {
		return durability;
	}

	/**
	 * @param durability durabilite de l'arme
	 */
	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	
	/**
	 * methode qui décrémente la durabilite de 1
	 */
	public void use() {
		this.durability--;
	}
	
	/**
	 * methode indiquant si l'arme est cassée
	 * @return true si elle est cassée, false sinon
	 */
	public boolean isBroken() {
		return this.durability <= 0;
	}
	
	/**
	 * methode affichant les statistiques d'une arme en console
	 * @return String de type "Weapon (MIN:min MAX:max STAMINA:stam DURABILITY:dur)"
	 */
	public String toString() {
		return String.format("%2s %2s %2s %2s", this.getName() + " (" + Weapon.MIN_STAT_STRING + ":" + this.getMinDamage(), Weapon.MAX_STAT_STRING + ":" + this.getMaxDamage(), Weapon.STAMINA_STAT_STRING + ":" + this.getStamCost(), Weapon.DURABILITY_STAT_STRING + ":" + this.getDurability() + ")");
	}
	
	
	/**
	 * methode qui remonte la durabilite de l'arme 
	 * @param kit kit de reparation
	 * @throws ConsumeNullException 
	 */
	public void repairWith(RepairKit kit) throws ConsumeNullException { 
		if (kit == null) {
			throw new ConsumeNullException();
		}
		this.setDurability(this.getDurability() + kit.use());
	}
	
	/**
	 * @return 2 poids de l'arme (2kg)
	 */
	@Override
	public int getWeight() {
		return 2;
	}
	
}
