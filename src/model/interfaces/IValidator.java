package model.interfaces;

import model.exceptions.InvalidTypeCardException;

public interface IValidator<T> {
    Boolean validate(T t);
}
