/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author xindi
 */
@Entity
public class FinancialQuarter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int year;
    private int quarter;

    public FinancialQuarter(int year, int quarter) {
        this.year = year;
        this.quarter = quarter;
    }

    public FinancialQuarter() {
    }
    
    

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinancialQuarter)) {
            return false;
        }
        FinancialQuarter other = (FinancialQuarter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
       

    @Override
    public String toString() {
        return "entity.FinancialQuarter[ id=" + id + " ]";
    }
    
    //1 = this > q2 (current fq is later than q2)
    //2 = this < q2 (current fq is earlier than q2)
    //3 = this = q2
    public int compareTo(FinancialQuarter q2) {
        int q2year = q2.getYear();
        int q2quart = q2.getQuarter();
        
        if(this.year > q2year) {
            return 1;
        } else if(this.year == q2year) {
            if (this.quarter > q2quart) {
                return 1;
            } else if(this.quarter == q2quart) {
                return 3;
            } else {
                return 2;
            }
        } else {
            return 2;
        }
    }
    
    
    public static FinancialQuarter getLaterQuarter(FinancialQuarter q1, FinancialQuarter q2) {
        int q1year = q1.getYear();
        int q2year = q2.getYear();
        int q1quart = q1.getQuarter();
        int q2quart = q2.getQuarter();
        
        if(q1year > q2year) {
            return q1;
        } else if(q1year == q2year) {
            if (q1quart > q2quart) {
                return q1;
            } else if(q1quart == q2quart) {
                return q1;
            } else {
                return q2;
            }
        } else {
            return q2;
        }
    }
    
    //1 = q1 later
    //2 = q2 later
    //3 = same financial quarter
    public static int compareQuarters(FinancialQuarter q1, FinancialQuarter q2) {
        int q1year = q1.getYear();
        int q2year = q2.getYear();
        int q1quart = q1.getQuarter();
        int q2quart = q2.getQuarter();
        
        if(q1year > q2year) {
            return 1;
        } else if(q1year == q2year) {
            if (q1quart > q2quart) {
                return 1;
            } else if(q1quart == q2quart) {
                return 3;
            } else {
                return 2;
            }
        } else {
            return 2;
        }
    }   
    
}
