package com.zeni.accountssale.presistance.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
    
    protected List<String> errors;
    protected boolean isValid;
    protected UUID id;
    
    protected Entity(UUID id) {
        errors = new ArrayList<>();
        this.id = id;
    }
    
    public UUID getId() {
        return id;
    }
    
    public boolean isValid() {
        return errors.isEmpty();
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}