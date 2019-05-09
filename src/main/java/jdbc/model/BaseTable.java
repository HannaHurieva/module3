package jdbc.model;

import java.util.Objects;

// Базовый класс модели, имеющий ключ id
public class BaseTable {
    protected long id;

    public BaseTable() {
    }

    public BaseTable(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTable baseModel = (BaseTable) o;
        return id == baseModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
