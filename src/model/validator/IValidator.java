package model.validator;

public interface IValidator<T> {
    Boolean validate(T t);
}
