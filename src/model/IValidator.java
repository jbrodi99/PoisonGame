package model;

public interface IValidator<T> {
    Boolean validate(T t);
}
