package com.epamTasks.task1.entity;

public abstract class Entity {
    protected long id;
    protected Entity() {
        this.id = 0;
    }
    protected Entity(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String toString() {
        return Long.toString(this.id);
    }
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Entity entity = (Entity) obj;
        return entity.getId() == this.getId();
    }
    public int hashCode() {
        return (int) this.getId();
    }
}
