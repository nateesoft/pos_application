package test;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class GridLayoutExample implements ActionListener{

    JFrame guiFrame;
    JPanel buttonPanel;
    JTextField numberPressed;
    JComboBox hGap;
    JComboBox vGap;
    JComboBox rowcol;

    
    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
    public static void main(String[] args) {
     
         //Use the event dispatch thread for Swing components
         EventQueue.invokeLater(new Runnable()
         {
             
            @Override
             public void run()
             {
                 
                 new GridLayoutExample();         
             }
         });
              
    }
    
    public GridLayoutExample()
    {
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("FlowLayout Example");
        guiFrame.setSize(300,300);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        numberPressed = new JTextField();
        numberPressed.setHorizontalAlignment(JTextField.RIGHT);
        
        guiFrame.add(numberPressed, BorderLayout.NORTH);
        
        JPanel optionPanel = new JPanel();
        Border outline = BorderFactory.createLineBorder(Color.black);
        optionPanel.setBorder(outline);

        //Use a BoxLayout to position of the option components
        //in the optionPanel in a column
        optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.Y_AXIS));        
   
        JButton changeLayout = new JButton("Change Layout");
        changeLayout.setActionCommand("Change Layout");
        changeLayout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //When the "Change Layout" button is clicked
                //the GridLayout of the buttonPanel is changed
                //based on the grid size and spacing values chosen
                GridLayout grid = new GridLayout(3,4);
                if (rowcol.getSelectedItem().equals("2,5"))
                {
                   grid.setRows(2);
                   grid.setColumns(5);
                }
                else if(rowcol.getSelectedItem().equals("3,4"))
                {
                   grid.setRows(3);
                   grid.setColumns(4);
                }
                else
                {
                   grid.setRows(4);
                   grid.setColumns(3);
                }                    

                grid.setHgap((Integer)hGap.getSelectedItem());
                grid.setVgap((Integer)vGap.getSelectedItem());
                buttonPanel.setLayout(grid);
                buttonPanel.revalidate();
            }
        });
        

        Integer[] options = {0,5,10,15};
        hGap = new JComboBox(options);
        hGap.setSelectedIndex(0);
        vGap = new JComboBox(options);
        vGap.setSelectedIndex(0);
      
        String[] rowscols = {"2,5", "3,4", "4,3"};
        rowcol = new JComboBox(rowscols);
        rowcol.setSelectedIndex(1);

                
        //Use a dimension object to limit the size of
        //the combo boxes
        Dimension comboSize = new Dimension(100,100);
        hGap.setMaximumSize(comboSize);
        vGap.setMaximumSize(comboSize);
        rowcol.setMaximumSize(comboSize);
        
        //Use the setAlignmentX method to push the components
        //to the left edge of the optionPanel
        hGap.setAlignmentX(Component.LEFT_ALIGNMENT);
        vGap.setAlignmentX(Component.LEFT_ALIGNMENT);
        rowcol.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel horizontalGap = new JLabel("Horizontal Gap:");
        JLabel verticalGap = new JLabel("Vertical Gap:");
        JLabel gridSize = new JLabel("Grid Size:");
        
        optionPanel.add(gridSize);
        optionPanel.add(rowcol);
        optionPanel.add(horizontalGap);
        optionPanel.add(hGap);
        optionPanel.add(verticalGap);
        optionPanel.add(vGap);
        optionPanel.add(changeLayout);
        guiFrame.add(optionPanel, BorderLayout.WEST);
        
        buttonPanel = new JPanel();
               
        //Make a Grid that has three rows and four columns
        buttonPanel.setLayout(new GridLayout(3,4));        
        
        guiFrame.add(buttonPanel, BorderLayout.CENTER);
        
        //Add the number buttons
        for (int i=1;i<11;i++)
        {
            addButton(buttonPanel, String.valueOf(i));
        }

        guiFrame.setVisible(true);  
    }
    
    //All the buttons are following the same pattern
    //so create them all in one place.
    private void addButton(Container parent, String name)
    {
        JButton but = new JButton(name);
        but.setActionCommand(name);
        but.addActionListener(this);
        parent.add(but);
    }
    
    //As all the buttons are doing the same thing it's
    //easier to make the class implement the ActionListener
    //interface and control the button clicks from one place
    @Override
    public void actionPerformed(ActionEvent event)
    {
        //get the Action Command text from the button
        String action = event.getActionCommand();
        
        //set the text using the Action Command text
        numberPressed.setText(action);
        
    }
}