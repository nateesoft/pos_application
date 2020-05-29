/**
 * Copyright (c) 2011 Bernhard Pauler, Tim Molderez.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the 3-Clause BSD License
 * which accompanies this distribution, and is available at
 * http://www.opensource.org/licenses/BSD-3-Clause
 */

package balloon;

import balloon.BehaviourTab;
import balloon.ContentsTab;
import balloon.LayersTab;
import balloon.LooksTab;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainPanel extends JPanel {
	public MainPanel() {
		super();
		setLayout(new GridBagLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		tabbedPane.addTab("Looks", new LooksTab());
		tabbedPane.addTab("Contents", new ContentsTab());
		tabbedPane.addTab("Behaviour", new BehaviourTab());
		tabbedPane.addTab("Types", new TypesTab());
		tabbedPane.addTab("Layers", new LayersTab());
		tabbedPane.addTab("Utilities", new UtilitiesTab());
		
		add(tabbedPane, new GridBagConstraints(0,0,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,10,10,10), 0, 0));
	}
}
