package model.factorys;

import model.interfaces.ICenterStack;

import java.util.List;

public interface ICenterFactory {
    List<ICenterStack> createCenter();
}
