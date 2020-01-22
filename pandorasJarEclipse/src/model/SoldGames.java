package model;

import utility.Acquisto;

import java.util.ArrayList;
import java.util.TreeMap;

public class SoldGames
{
    TreeMap<Integer,Integer> soldGPerYear;
    TreeMap<Integer,Double> earnedMoneyPerYear;
    TreeMap<Integer,Integer> soldGPerMonth;
    TreeMap<Integer,Double> earnedMoneyPerMonth;

    public TreeMap<Integer, Integer> getSoldGPerMonth() {
        return soldGPerMonth;
    }

    public void setSoldGPerMonth(TreeMap<Integer, Integer> soldGPerMonth) {
        this.soldGPerMonth = soldGPerMonth;
    }

    public TreeMap<Integer, Double> getEarnedMoneyPerMonth() {
        return earnedMoneyPerMonth;
    }

    public void setEarnedMoneyPerMonth(TreeMap<Integer, Double> earnedMoneyPerMonth) {
        this.earnedMoneyPerMonth = earnedMoneyPerMonth;
    }

    public TreeMap<Integer, Integer> getSoldGPerYear() {
        return soldGPerYear;
    }

    public void setSoldGPerYear(TreeMap<Integer, Integer> soldGPerYear) {
        this.soldGPerYear = soldGPerYear;
    }

    public TreeMap<Integer, Double> getEarnedMoneyPerYear() {
        return earnedMoneyPerYear;
    }

    public void setEarnedMoneyPerYear(TreeMap<Integer, Double> earnedMoneyPerYear) {
        this.earnedMoneyPerYear = earnedMoneyPerYear;
    }
}
