package com.team14.cms;

public interface UserPartVisitor  {
    public void visit(Administration administration);
    public void visit(Professor professor);
    public void visit(Student student);
}
