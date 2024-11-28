package model.factorys;

import model.enums.TYPECARD;
import model.interfaces.ICenterStack;
import model.logic.CenterStack;

import java.util.List;

public class CenterFactory implements ICenterFactory{
    @Override
    public List<ICenterStack> createCenter() {
        return List.of(
                new CenterStack(TYPECARD.SWORD),
                new CenterStack(TYPECARD.GOBLET),
                new CenterStack(TYPECARD.GOLDEN_COIN)
        );
    }
}