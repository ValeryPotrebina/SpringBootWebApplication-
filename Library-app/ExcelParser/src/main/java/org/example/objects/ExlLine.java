package org.example.objects;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class ExlLine {
    private Date date;
    private PaymentType paymentType;
    private double paymentInvoice;
    private String customerName;
    private String executorName;
    private String techniqueName;
    private String account;
    private double workSum;
    private double NDS;
    private double dispatcherSum;
    private String remark;
    private String ReferenceNum;
    private String actNum;
    private String organization;
    private double sumWithNDS;

    public ExlLine() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExlLine{");
        sb.append("date=").append(date);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", paymentInvoice=").append(paymentInvoice);
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", executorName='").append(executorName).append('\'');
        sb.append(", techniqueName='").append(techniqueName).append('\'');
        sb.append(", account='").append(account).append('\'');
        sb.append(", workSum=").append(workSum);
        sb.append(", NDS=").append(NDS);
        sb.append(", dispatcherSum=").append(dispatcherSum);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", ReferenceNum=").append(ReferenceNum);
        sb.append(", actNum='").append(actNum).append('\'');
        sb.append(", organization='").append(organization).append('\'');
        sb.append(", sumWithNDS=").append(sumWithNDS);
        sb.append('}');
        return sb.toString();
    }
}
