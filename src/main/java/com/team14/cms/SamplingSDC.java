package com.team14.cms;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy
 */
public interface SamplingSDC {
    public List<Student> getSample(List<Student> students);
}

// Returns a list of the first x students
class FirstXSampling implements SamplingSDC {
    int x;

    public FirstXSampling (int x) {
        this.x = x;
    }

    public List<Student> getSample(List<Student> students) {
        List<Student> sample = new ArrayList<>();
        int count = 0;
        while (count < x) {
            sample.add(students.get(count++));
        }
        return sample;
    }
}

// Returns a list of students after getting every nth item
class SkipNSampling implements SamplingSDC {
    int n;

    public SkipNSampling (int n) {
        this.n = n;
    }

    public List<Student> getSample(List<Student> students) {
        List<Student> sample = new ArrayList<>();
        for (int i = 0; i < students.size(); i += n) {
            sample.add(students.get(i));
        }
        return sample;
    }
}

// Returns students with year standing of x
class YearStandingXSampling implements SamplingSDC {
    int x;

    public YearStandingXSampling (int x) {
        this.x = x;
    }

    public List<Student> getSample(List<Student> students) {
        List<Student> sample = new ArrayList<>();
        for (Student s : students) {
            if (s.yearStanding == x) {
                sample.add(s);
            }
        }
        return sample;
    }
}

// Returns every male student
class SexSampling implements SamplingSDC {
    User.Sex sex;

    public SexSampling(User.Sex sex) {
        this.sex = sex;
    }

    public List<Student> getSample(List<Student> students) {
        List<Student> sample = new ArrayList<>();
        for (Student s : students) {
            if (s.sex == sex) {
                sample.add(s);
            }
        }
        return sample;
    }
}

/**
 * Decorator
 */
// Decorator marks every student on the deans list (mark over a certain cutoff)
class DeansList implements SamplingSDC {
    SamplingSDC strategy;
    float cutoff = 80;

    public DeansList(SamplingSDC strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<Student> getSample(List<Student> students) {
        List<Student> sample = new ArrayList<>();
        for (Student s : students) {
            if (s.getAvg() >= cutoff) {
                s.isOnDeansList = true;
            }
            sample.add(s);
        }
        return sample;
    }
}

/**
 * Composite
 */
// Composite combining strategies
class CompositeSampling implements SamplingSDC {
    List<SamplingSDC> strategies = new ArrayList<>();

    public void addStrategy(SamplingSDC strategy) {
        strategies.add(strategy);
    }

    @Override
    public List<Student> getSample(List<Student> students) {
        for (SamplingSDC strategy : strategies) {
            students = strategy.getSample(students);
        }
        return students;
    }
}