/*
    This file is part of Cyclos (www.cyclos.org).
    A project of the Social Trade Organisation (www.socialtrade.org).

    Cyclos is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    Cyclos is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Cyclos; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 */
package nl.strohalm.cyclos.entities.accounts.guarantees;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.accounts.guarantees.PaymentObligation.Status;
import nl.strohalm.cyclos.entities.members.Element;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Calendar;

@Cacheable
@Table(name = "payment_obligation_logs")
@javax.persistence.Entity
public class PaymentObligationLog extends Entity {

    public static enum Relationships implements Relationship {
        CERTIFICATION("paymentObligation"), BY("by");
        private final String name;

        private Relationships(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static final long serialVersionUID = -377309326263071194L;

    @Column(name = "date", nullable = false)
    private Calendar          date;

    @Column(name = "status", nullable = false, length = 2)
	private Status            status;

    @ManyToOne
    @JoinColumn(name = "payment_obligation_id", nullable = false)
	private PaymentObligation paymentObligation;

    @ManyToOne
    @JoinColumn(name = "by_id")
	private Element           by;

    public Element getBy() {
        return by;
    }

    public Calendar getDate() {
        return date;
    }

    public PaymentObligation getPaymentObligation() {
        return paymentObligation;
    }

    public Status getStatus() {
        return status;
    }

    public void setBy(final Element by) {
        this.by = by;
    }

    public void setDate(final Calendar date) {
        this.date = date;
    }

    public void setPaymentObligation(final PaymentObligation paymentObligation) {
        this.paymentObligation = paymentObligation;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + " - " + status;
    }
}
