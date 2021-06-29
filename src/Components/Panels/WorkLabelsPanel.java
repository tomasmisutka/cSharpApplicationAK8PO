package Components.Panels;

import Common.Employee;
import Common.WorkLabel;
import Components.WorkLabelComponent;
import Services.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class WorkLabelsPanel extends JPanel

{
    private boolean isVisible = true;
    private final static WorkLabelsPanel labelsPanel = new WorkLabelsPanel();
    private ArrayList<WorkLabel> workLabels = new ArrayList<>();

    private WorkLabelsPanel()
    {
        this.createPanel();
    }

    public static WorkLabelsPanel getInstance()
    {
        return labelsPanel;
    }

    private void createPanel()
    {
        this.setBorder(new EmptyBorder(0, 10, 0, 10));
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(isVisible);
        this.initContent();
    }

    public void changeVisibility()
    {
        isVisible = !isVisible;
        this.setVisible(isVisible);
        this.revalidate();
    }

    private void initContent()
    {
        workLabels = DBConnection.getInstance().getUnassignedWorkLabels();

        for (WorkLabel workLabel : workLabels)
        {
            WorkLabelComponent workLabelComponent = new WorkLabelComponent(workLabel);
            this.add(workLabelComponent);
        }
    }

    public boolean assignWorkLabelToEmployee(WorkLabelComponent workLabelToDelete, Employee employee)
    {
        boolean success = DBConnection.getInstance().updateEmployeeIdInWorkLabel(employee.getId(),
                workLabelToDelete.getWorkLabel().getId());
        if (success)
        {
            workLabels.remove(workLabelToDelete.getWorkLabel());
            this.remove(workLabelToDelete);
//            int indexToRemove = getWorkLabelPositionToRemove(workLabelToDelete);
//            this.remove(indexToRemove);
            this.revalidate();
            this.repaint();
        }
        return success;
    }


    // todo - maybe use it later
//    private int getWorkLabelPositionToRemove(WorkLabelComponent workLabelComponent)
//    {
//        int index = 0;
//        for (Component component : this.getComponents())
//        {
//            if (component instanceof WorkLabelComponent)
//            {
//                if (((WorkLabelComponent) component).getWorkLabel().getId() == workLabelComponent.getWorkLabel().getId())
//                    break;
//                else
//                    index++;
//            }
//        }
//        return index;
//    }

    public void addWorkLabelToPanel(WorkLabelComponent workLabelComponent)
    {
        boolean success = DBConnection.getInstance().updateEmployeeIdInWorkLabel(0, workLabelComponent.getWorkLabel().getId());
        if (success)
        {
            workLabels.add(workLabelComponent.getWorkLabel());
            this.add(workLabelComponent);
            this.revalidate();
        }
    }
}
