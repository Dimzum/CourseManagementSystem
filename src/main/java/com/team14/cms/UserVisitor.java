package com.team14.cms;

public interface UserVisitor {
    public void visit(Student student);
    public void visit(Professor professor);
}
