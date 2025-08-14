
package com.mycompany.jobportal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Method;


 public class HomePage extends JFrame {
    private List<Application> sharedApplications;

    private BackgroundPanel welcomeBackgroundPanel;   
    private BackgroundPanel mainBackgroundPanel;      

    private JPanel welcomePanel;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JPanel roleSelectionPanel;
    private Employer employerPanel;  
    private AdminPanel adminPanel;           
    private JobSeekerPanel jobSeekerPanel; 

    private HashMap<String, String> userDatabase = new HashMap<>();

    public HomePage() {
        // Initialize shared applications list 
        sharedApplications = new ArrayList<>();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setTitle("JobQuest - Online Job Portal");

        // Set icon
        try {
            Image icon = ImageIO.read(new File("C:\\Users\\sanji\\Downloads\\icon.png"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load background images
        Image welcomeBg = null;
        Image mainBg = null;
        try {
            welcomeBg = ImageIO.read(new File("C:\\Users\\sanji\\Downloads\\JobQuest.png"));
            mainBg = ImageIO.read(new File("C:\\Users\\sanji\\Downloads\\EE.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        welcomeBackgroundPanel = new BackgroundPanel(welcomeBg);
        welcomeBackgroundPanel.setLayout(new BorderLayout());

        mainBackgroundPanel = new BackgroundPanel(mainBg);
        mainBackgroundPanel.setLayout(new BorderLayout());

        createWelcomePanel();
        createLoginPanel();
        createRegisterPanel();
        createRoleSelectionPanel();

        // Initialize all panels with back navigation and shared applications
        employerPanel = new Employer(sharedApplications, this::showRoleSelectionPanel);
        adminPanel = new AdminPanel(this::showRoleSelectionPanel);
        jobSeekerPanel = new JobSeekerPanel(sharedApplications, this::showRoleSelectionPanel);

        welcomeBackgroundPanel.add(welcomePanel, BorderLayout.CENTER);
        setContentPane(welcomeBackgroundPanel);

        // Show the frame
        setVisible(true);
    }

    private void createWelcomePanel() {
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setOpaque(false);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 100));
        
        welcomePanel.add(Box.createVerticalGlue());
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel label = new JLabel("Welcome To JobQuest!", SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(600, 50));
        contentPanel.add(label);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(Color.decode("#6caf5f"));
        btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setMaximumSize(new Dimension(300, 70));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(btnLogin);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JButton btnRegister = new JButton("Register");
        btnRegister.setBackground(Color.decode("#6caf5f"));
        btnRegister.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setMaximumSize(new Dimension(300, 70));
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(btnRegister);
        
        JPanel horizontalContainer = new JPanel();
        horizontalContainer.setLayout(new BoxLayout(horizontalContainer, BoxLayout.X_AXIS));
        horizontalContainer.setOpaque(false);
        horizontalContainer.add(Box.createHorizontalGlue());
        horizontalContainer.add(contentPanel);
        horizontalContainer.add(Box.createRigidArea(new Dimension(50, 0)));
        
        welcomePanel.add(horizontalContainer);
        welcomePanel.add(Box.createVerticalGlue());
        
        btnLogin.addActionListener(e -> showLoginPanel());
        btnRegister.addActionListener(e -> showRegisterPanel());
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setOpaque(false);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        JLabel label = new JLabel("Login", SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 26));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(400, 40));
        loginPanel.add(label);

        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblUser.setForeground(Color.WHITE);
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblUser.setMaximumSize(new Dimension(400, 25));
        loginPanel.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtUser.setMaximumSize(new Dimension(400, 30));
        loginPanel.add(txtUser);

        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblPass.setForeground(Color.WHITE);
        lblPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPass.setMaximumSize(new Dimension(400, 25));
        loginPanel.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtPass.setMaximumSize(new Dimension(400, 30));
        loginPanel.add(txtPass);

        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setOpaque(false);

        JButton btnSubmit = new JButton("Login");
        btnSubmit.setBackground(Color.decode("#6caf5f"));
        btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setPreferredSize(new Dimension(120, 40));
        btnPanel.add(btnSubmit);

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(Color.decode("#6caf5f"));
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnBack.setForeground(Color.WHITE);
        btnBack.setPreferredSize(new Dimension(120, 40));
        btnPanel.add(btnBack);

        loginPanel.add(btnPanel);

        btnSubmit.addActionListener(e -> {
            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password.");
                return;
            }

            if (!userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "Username not found.");
            } else if (!userDatabase.get(username).equals(password)) {
                JOptionPane.showMessageDialog(this, "Incorrect password.");
            } else {
                showRoleSelectionPanel();
            }
        });

        btnBack.addActionListener(e -> showWelcomePanel());
    }

    private void createRegisterPanel() {
        registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setOpaque(false);
        registerPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        JLabel label = new JLabel("Register", SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 26));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(400, 40));
        registerPanel.add(label);

        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblUser.setForeground(Color.WHITE);
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblUser.setMaximumSize(new Dimension(400, 25));
        registerPanel.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtUser.setMaximumSize(new Dimension(400, 30));
        registerPanel.add(txtUser);

        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblPass.setForeground(Color.WHITE);
        lblPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPass.setMaximumSize(new Dimension(400, 25));
        registerPanel.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtPass.setMaximumSize(new Dimension(400, 30));
        registerPanel.add(txtPass);

        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel lblPassConfirm = new JLabel("Confirm Password:");
        lblPassConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblPassConfirm.setForeground(Color.WHITE);
        lblPassConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPassConfirm.setMaximumSize(new Dimension(400, 25));
        registerPanel.add(lblPassConfirm);

        JPasswordField txtPassConfirm = new JPasswordField();
        txtPassConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtPassConfirm.setMaximumSize(new Dimension(400, 30));
        registerPanel.add(txtPassConfirm);

        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setOpaque(false);

        JButton btnSubmit = new JButton("Register");
        btnSubmit.setBackground(Color.decode("#6caf5f"));
        btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setPreferredSize(new Dimension(120, 40));
        btnPanel.add(btnSubmit);

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(Color.decode("#6caf5f"));
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnBack.setForeground(Color.WHITE);
        btnBack.setPreferredSize(new Dimension(120, 40));
        btnPanel.add(btnBack);

        registerPanel.add(btnPanel);

        btnSubmit.addActionListener(e -> {
            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword());
            String passwordConfirm = new String(txtPassConfirm.getPassword());

            if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
                return;
            }

            if (!password.equals(passwordConfirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
                return;
            }

            userDatabase.put(username, password);
            JOptionPane.showMessageDialog(this, "Registration successful! Please login.");
            showLoginPanel();
        });

        btnBack.addActionListener(e -> showWelcomePanel());
    }

    private void createRoleSelectionPanel() {
        roleSelectionPanel = new JPanel();
        roleSelectionPanel.setLayout(new BoxLayout(roleSelectionPanel, BoxLayout.Y_AXIS));
        roleSelectionPanel.setOpaque(false);
        roleSelectionPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel label = new JLabel("Select your role", SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(400, 40));
        roleSelectionPanel.add(label);

        roleSelectionPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        String[] roles = {"Jobseeker", "Employer", "Admin"};

        for (String role : roles) {
            JButton btn = new JButton(role);
            btn.setBackground(Color.decode("#6caf5f"));
            btn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            btn.setForeground(Color.WHITE);
            btn.setMaximumSize(new Dimension(400, 50));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            roleSelectionPanel.add(btn);
            roleSelectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            btn.addActionListener(e -> {
                String selectedRole = ((JButton) e.getSource()).getText();
                switch (selectedRole) {
                    case "Employer":
                        showEmployer();
                        break;
                    case "Admin":
                        showAdmin();
                        break;
                    case "Jobseeker":
                        showJobSeeker();
                        break;
                }
            });
        }
    }

    private void showWelcomePanel() {
        setContentPane(welcomeBackgroundPanel);
        welcomeBackgroundPanel.removeAll();
        welcomeBackgroundPanel.add(welcomePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showLoginPanel() {
        setContentPane(mainBackgroundPanel);
        mainBackgroundPanel.removeAll();
        mainBackgroundPanel.add(loginPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showRegisterPanel() {
        setContentPane(mainBackgroundPanel);
        mainBackgroundPanel.removeAll();
        mainBackgroundPanel.add(registerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showRoleSelectionPanel() {
        setContentPane(mainBackgroundPanel);
        mainBackgroundPanel.removeAll();
        mainBackgroundPanel.add(roleSelectionPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showEmployer() {
        setContentPane(mainBackgroundPanel);
        mainBackgroundPanel.removeAll();
        mainBackgroundPanel.add(employerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showAdmin() {
        setContentPane(mainBackgroundPanel);
        mainBackgroundPanel.removeAll();
        mainBackgroundPanel.add(adminPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showJobSeeker() {
        setContentPane(mainBackgroundPanel);
        mainBackgroundPanel.removeAll();
        mainBackgroundPanel.add(jobSeekerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
       public List<Application> getSharedApplications() {
        return sharedApplications;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomePage();
        });
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image image) {
        this.backgroundImage = image;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class Employer extends JPanel {
    private static final String JOBS_FILE = "jobs.dat";
    private static final String APPLICATIONS_FILE = "applications.dat";
    private List<Application> sharedApplications;

    private List<Job> jobs;
    private List<Application> applications;
    private Image bgImage;

    public Employer(List<Application> sharedApplications, Runnable onBack) {
        try {
            bgImage = ImageIO.read(new File("C:\\Users\\sanji\\Downloads\\employer_background1.jpg"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Store reference to shared applications
        this.sharedApplications = sharedApplications;

        jobs = StorageHelper.loadData(JOBS_FILE);
        applications = StorageHelper.loadData(APPLICATIONS_FILE);
        
        for (Application app : sharedApplications) {
            if (!applications.contains(app)) {
                applications.add(app);
            }
        }

        JLabel label = new JLabel("Employer Dashboard", SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(400, 40));
        label.setPreferredSize(new Dimension(400, 40));
        label.setMinimumSize(new Dimension(400, 40));
        add(label);

        add(Box.createRigidArea(new Dimension(0, 30))); 

        String[] options = {
            "Post Job",
            "Edit/Delete Job",
            "View Applications",
            "Accept Candidates",
            "Reject Candidates"
        };

        for (String option : options) {
            JButton btn = new JButton(option);
            btn.setBackground(Color.decode("#327566"));
            btn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            btn.setForeground(Color.WHITE);
            btn.setMaximumSize(new Dimension(500, 50));
            btn.setPreferredSize(new Dimension(500, 50));
            btn.setMinimumSize(new Dimension(500, 50));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);

            btn.addActionListener(e -> handleAction(option));
            add(btn);
            add(Box.createRigidArea(new Dimension(0, 15))); 
        }

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(Color.decode("#327566"));
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnBack.setForeground(Color.WHITE);
        btnBack.setMaximumSize(new Dimension(200, 40));
        btnBack.setPreferredSize(new Dimension(200, 40));
        btnBack.setMinimumSize(new Dimension(200, 40));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());  
        add(btnBack);

        btnBack.addActionListener(e -> {
            StorageHelper.saveData((ArrayList<Job>) jobs, JOBS_FILE);
            StorageHelper.saveData((ArrayList<Application>) applications, APPLICATIONS_FILE);
            if (onBack != null) {
                onBack.run();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void handleAction(String action) {
        syncApplications();
        
        switch (action) {
            case "Post Job":
                postJob();
                break;
            case "Edit/Delete Job":
                editDeleteJob();
                break;
            case "View Applications":
                viewApplications();
                break;
            case "Accept Candidates":
                acceptReject(true);
                break;
            case "Reject Candidates":
                acceptReject(false);
                break;
        }
    }

    private void syncApplications() {
        for (Application app : sharedApplications) {
            if (!applications.contains(app)) {
                applications.add(app);
            }
        }
        
        // Update shared list with any status changes
        for (int i = 0; i < sharedApplications.size(); i++) {
            Application sharedApp = sharedApplications.get(i);
            for (Application localApp : applications) {
                if (localApp.getCandidateName().equals(sharedApp.getCandidateName()) && 
                    localApp.getJobTitle().equals(sharedApp.getJobTitle())) {
                    sharedApp.setStatus(localApp.getStatus());
                    break;
                }
            }
        }
    }

    private void postJob() {
        JFrame postJobFrame = new JFrame("Post Job");
        try {
            Image icon = ImageIO.read(new File("C:\\Users\\sanji\\Downloads\\icon.png"));
            postJobFrame.setIconImage(icon);  
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        postJobFrame.setSize(800, 600);
        postJobFrame.setLocationRelativeTo(null);
        postJobFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(600, 40));
        titleField.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleField.setPreferredSize(new Dimension(600, 40));
        titleField.setMinimumSize(new Dimension(600, 40));

        JTextArea descArea = new JTextArea();
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel descPanel = new JPanel(new BorderLayout());
        descPanel.setMaximumSize(new Dimension(600, 200));
        descPanel.setPreferredSize(new Dimension(600, 200));
        descPanel.setMinimumSize(new Dimension(600, 200));
        descPanel.add(descArea, BorderLayout.CENTER);

        JTextField locationField = new JTextField();
        locationField.setMaximumSize(new Dimension(600, 40));
        locationField.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationField.setPreferredSize(new Dimension(600, 40));
        locationField.setMinimumSize(new Dimension(600, 40));

        JTextField salaryField = new JTextField();
        salaryField.setMaximumSize(new Dimension(600, 40));
        salaryField.setAlignmentX(Component.CENTER_ALIGNMENT);
        salaryField.setPreferredSize(new Dimension(600, 40));
        salaryField.setMinimumSize(new Dimension(600, 40));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Job Title:");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(titleLabel);
        titleField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(titleField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(descLabel);
        formPanel.add(descPanel);  
        descPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(locationLabel);
        locationField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(locationField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(salaryLabel);
        salaryField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(salaryField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JButton postButton = new JButton("Post Job");
        postButton.setPreferredSize(new Dimension(120, 40));
        postButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        postButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String description = descArea.getText().trim();
            String location = locationField.getText().trim();
            String salaryStr = salaryField.getText().trim();

            if (title.isEmpty() || description.isEmpty() || location.isEmpty() || salaryStr.isEmpty()) {
                JOptionPane.showMessageDialog(postJobFrame, "All fields must be filled.");
                return;
            }

            double salary;
            try {
                salary = Double.parseDouble(salaryStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(postJobFrame, "Invalid salary format.");
                return;
            }

            jobs.add(new Job(title, description, location, salary));
            JOptionPane.showMessageDialog(postJobFrame, "Job posted successfully!");
            postJobFrame.dispose();
        });

        formPanel.add(postButton);

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(formPanel);
        postJobFrame.add(wrapperPanel, BorderLayout.CENTER);
        postJobFrame.setVisible(true);
    }

    private void editDeleteJob() {
        if (jobs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No jobs to edit/delete.");
            return;
        }
        String[] jobTitles = jobs.stream().map(Job::getTitle).toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(this, "Select job to edit/delete:",
                "Edit/Delete Job", JOptionPane.PLAIN_MESSAGE, null, jobTitles, jobTitles[0]);
        if (selected == null) return;

        Job job = jobs.stream().filter(j -> j.getTitle().equals(selected)).findFirst().orElse(null);
        if (job == null) return;

        String[] options = {"Edit", "Delete"};
        int choice = JOptionPane.showOptionDialog(this, "What do you want to do?",
                "Edit/Delete", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (choice == 0) {
            JTextField titleField = new JTextField(job.getTitle());
            titleField.setMaximumSize(new Dimension(600, 40));
            titleField.setPreferredSize(new Dimension(600, 40));
            titleField.setMinimumSize(new Dimension(600, 40));
            titleField.setAlignmentX(Component.CENTER_ALIGNMENT);

            JTextArea descArea = new JTextArea(job.getDescription());
            descArea.setLineWrap(true);
            descArea.setWrapStyleWord(true);

            JPanel descPanel = new JPanel(new BorderLayout());
            descPanel.setMaximumSize(new Dimension(600, 200));
            descPanel.setPreferredSize(new Dimension(600, 200));
            descPanel.setMinimumSize(new Dimension(600, 200));
            descPanel.add(descArea, BorderLayout.CENTER);
            descPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JTextField locationField = new JTextField(job.getLocation());
            locationField.setMaximumSize(new Dimension(600, 40));
            locationField.setPreferredSize(new Dimension(600, 40));
            locationField.setMinimumSize(new Dimension(600, 40));
            locationField.setAlignmentX(Component.CENTER_ALIGNMENT);

            JTextField salaryField = new JTextField(String.valueOf(job.getSalary()));
            salaryField.setMaximumSize(new Dimension(600, 40));
            salaryField.setPreferredSize(new Dimension(600, 40));
            salaryField.setMinimumSize(new Dimension(600, 40));
            salaryField.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            panel.add(new JLabel("Job Title:"));
            panel.add(titleField);
            panel.add(Box.createRigidArea(new Dimension(0, 8)));

            panel.add(new JLabel("Description:"));
            panel.add(descPanel);
            panel.add(Box.createRigidArea(new Dimension(0, 8)));

            panel.add(new JLabel("Location:"));
            panel.add(locationField);
            panel.add(Box.createRigidArea(new Dimension(0, 8)));

            panel.add(new JLabel("Salary:"));
            panel.add(salaryField);
            panel.add(Box.createRigidArea(new Dimension(0, 8)));

            int result = JOptionPane.showConfirmDialog(this, panel, "Edit Job",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String newTitle = titleField.getText().trim();
                String newDescription = descArea.getText().trim();
                String newLocation = locationField.getText().trim();
                String salaryStr = salaryField.getText().trim();

                if (newTitle.isEmpty() || newDescription.isEmpty() || newLocation.isEmpty() || salaryStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled.");
                    return;
                }

                double salary;
                try {
                    salary = Double.parseDouble(salaryStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid salary format.");
                    return;
                }

                job.setTitle(newTitle);
                job.setDescription(newDescription);
                job.setLocation(newLocation);
                job.setSalary(salary);
                JOptionPane.showMessageDialog(this, "Job updated!");
            }
        } else if (choice == 1) { // Delete
            jobs.remove(job);
            JOptionPane.showMessageDialog(this, "Job deleted!");
        }
    }

    private void viewApplications() {
        syncApplications(); // Ensure we have latest applications
        
        if (applications.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No applications found.");
            return;
        }
        StringBuilder sb = new StringBuilder("Applications:\n");
        for (Application app : applications) {
            sb.append(app.getCandidateName())
              .append(" - Applied for: ").append(app.getJobTitle())
              .append("\nStatus: ").append(app.getStatus())
              .append("\n\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Applications",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void acceptReject(boolean accept) {
        syncApplications(); // Ensure we have latest applications
        
        List<Application> pending = new ArrayList<>();
        for (Application app : applications) {
            if ("Pending".equals(app.getStatus())) {
                pending.add(app);
            }
        }
        if (pending.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No pending applications.");
            return;
        }
        String[] candidates = pending.stream()
                .map(a -> a.getCandidateName() + " (" + a.getJobTitle() + ")")
                .toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(this,
                "Select candidate to " + (accept ? "accept" : "reject") + ":",
                "Manage Applications", JOptionPane.PLAIN_MESSAGE, null,
                candidates, candidates[0]);
        if (selected == null) return;

        Application app = pending.stream()
                .filter(a -> (a.getCandidateName() + " (" + a.getJobTitle() + ")").equals(selected))
                .findFirst().orElse(null);
        if (app != null) {
            app.setStatus(accept ? "Accepted" : "Rejected");
            
            for (Application sharedApp : sharedApplications) {
                if (sharedApp.getCandidateName().equals(app.getCandidateName()) && 
                    sharedApp.getJobTitle().equals(app.getJobTitle())) {
                    sharedApp.setStatus(app.getStatus());
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(this, "Candidate " + app.getStatus() + "!");
        }
    }
}

class AdminPanel extends JPanel {
    private Features features;
    private Runnable onBack;

    public AdminPanel(Runnable onBack) {
        this.onBack = onBack;
        features = new Features();
        
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel title = new JLabel("Admin Panel");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
        title.setForeground(Color.WHITE); 
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        add(title);

        String[] btnLabels = {
            "Approve/Reject User Accounts",
            "Manage Job Postings",
            "View Applications",
            "Remove/Suspend Accounts",
            "View Analytics"
        };

        for (String label : btnLabels) {
            JButton btn = createStyledButton(label);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setPreferredSize(new Dimension(300, 45)); 
            btn.setMaximumSize(new Dimension(300, 45));
            btn.addActionListener(e -> invokeFeature(label));
            add(btn);
            add(Box.createRigidArea(new Dimension(0, 15)));
        }

        // Add back button
        JButton btnBack = new JButton("Back");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setPreferredSize(new Dimension(200, 40));
        btnBack.setMaximumSize(new Dimension(200, 40));
        btnBack.setBackground(Color.decode("#327566"));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnBack.addActionListener(e -> {
            if (this.onBack != null) {
                this.onBack.run();
            }
        });
        add(Box.createVerticalGlue());
        add(btnBack);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Apply a semi-transparent dark overlay for better text visibility
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(46, 45, 43, 180));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(new Color(200, 200, 200));
        button.setForeground(Color.BLACK);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(180, 180, 180));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(200, 200, 200));
            }
        });
        return button;
    }

    private void invokeFeature(String label) {
        String methodName = switch (label) {
            case "Approve/Reject User Accounts" -> "approveRejectUsers";
            case "Manage Job Postings" -> "manageJobPostings";
            case "View Applications" -> "viewApplications";
            case "Remove/Suspend Accounts" -> "removeSuspendAccounts";
            case "View Analytics" -> "viewAnalytics";
            default -> null;
        };

        if (methodName != null) {
            try {
                Method method = Features.class.getDeclaredMethod(methodName);
                method.setAccessible(true);
                method.invoke(features);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                    "Error invoking feature: " + label + "\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

// Features class for Admin Panel
class Features extends JFrame {
    private ArrayList<String> pendingUsers = new ArrayList<>();
    private ArrayList<String> approvedUsers = new ArrayList<>();
    private ArrayList<String> rejectedUsers = new ArrayList<>();
    private ArrayList<String> jobPosts = new ArrayList<>();
    private ArrayList<String> applications = new ArrayList<>();
    private ArrayList<String> suspendedAccounts = new ArrayList<>();

    public Features() {
        setTitle("Admin Panel - Features");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sample Data
        pendingUsers.add("Nilanjana");
        pendingUsers.add("Sanjida");
        pendingUsers.add("Zareen");
        jobPosts.add("Software Engineer");
        jobPosts.add("Data Analyst");
        applications.add("Application #1");
        applications.add("Application #2");
        applications.add("Application #3");
        suspendedAccounts.add("Prianka");
    }

    private void approveRejectUsers() {
        JFrame frame = new JFrame("Approve/Reject Users");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        DefaultListModel<String> model = new DefaultListModel<>();
        pendingUsers.forEach(model::addElement);
        JList<String> list = new JList<>(model);
        styleList(list);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setBackground(Color.BLACK);

        JButton approveBtn = new JButton("Approve");
        styleButton(approveBtn, new Color(0, 128, 0));

        JButton rejectBtn = new JButton("Reject");
        styleButton(rejectBtn, new Color(128, 0, 0));

        btnPanel.add(approveBtn);
        btnPanel.add(rejectBtn);

        JLabel countersLabel = new JLabel(getCountersText());
        countersLabel.setForeground(Color.WHITE);
        countersLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        countersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(btnPanel, BorderLayout.NORTH);
        bottomPanel.add(countersLabel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        approveBtn.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null) {
                approvedUsers.add(selected);
                pendingUsers.remove(selected);
                model.removeElement(selected);
                countersLabel.setText(getCountersText());
                JOptionPane.showMessageDialog(frame, selected + " Approved!");
            }
        });

        rejectBtn.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null) {
                rejectedUsers.add(selected);
                pendingUsers.remove(selected);
                model.removeElement(selected);
                countersLabel.setText(getCountersText());
                JOptionPane.showMessageDialog(frame, selected + " Rejected!");
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void manageJobPostings() {
        JFrame frame = new JFrame("Manage Job Postings");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        DefaultListModel<String> model = new DefaultListModel<>();
        jobPosts.forEach(model::addElement);
        JList<String> list = new JList<>(model);
        styleList(list);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.BLACK);
        JTextField jobField = new JTextField(15);
        jobField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JButton addBtn = new JButton("Add");
        styleButton(addBtn, new Color(0, 120, 215));
        JButton removeBtn = new JButton("Remove");
        styleButton(removeBtn, new Color(192, 57, 43));

        inputPanel.add(jobField);
        inputPanel.add(addBtn);
        inputPanel.add(removeBtn);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            String job = jobField.getText().trim();
            if (!job.isEmpty()) {
                model.addElement(job);
                jobPosts.add(job);
                jobField.setText("");
            }
        });

        removeBtn.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null) {
                model.removeElement(selected);
                jobPosts.remove(selected);
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void viewApplications() {
        JFrame frame = new JFrame("View Applications");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        DefaultListModel<String> model = new DefaultListModel<>();
        applications.forEach(model::addElement);
        JList<String> list = new JList<>(model);
        styleList(list);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void removeSuspendAccounts() {
        JFrame frame = new JFrame("Remove/Suspend Accounts");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        DefaultListModel<String> model = new DefaultListModel<>();
        suspendedAccounts.forEach(model::addElement);
        JList<String> list = new JList<>(model);
        styleList(list);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton removeBtn = new JButton("Remove");
        styleButton(removeBtn, new Color(192, 57, 43));

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.BLACK);
        btnPanel.add(removeBtn);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        removeBtn.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null) {
                model.removeElement(selected);
                suspendedAccounts.remove(selected);
                JOptionPane.showMessageDialog(frame, selected + " account removed.");
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void viewAnalytics() {
        JFrame frame = new JFrame("View Analytics");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextArea analytics = new JTextArea();
        analytics.setEditable(false);
        analytics.setBackground(Color.BLACK);
        analytics.setForeground(Color.WHITE);
        analytics.setFont(new Font("Segoe UI", Font.BOLD, 14));
        analytics.setText(
                "Total Pending Users: " + pendingUsers.size() +
                "\nTotal Approved Users: " + approvedUsers.size() +
                "\nTotal Rejected Users: " + rejectedUsers.size() +
                "\nTotal Job Posts: " + jobPosts.size() +
                "\nTotal Applications: " + applications.size() +
                "\nSuspended Accounts: " + suspendedAccounts.size()
        );

        JScrollPane scrollPane = new JScrollPane(analytics);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void styleList(JList<String> list) {
        list.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        list.setSelectionBackground(Color.DARK_GRAY);
        list.setSelectionForeground(Color.WHITE);
        list.setBackground(Color.BLACK);
        list.setForeground(Color.WHITE);
        list.setFixedCellHeight(25);
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private String getCountersText() {
        return String.format("Pending: %d    Approved: %d    Rejected: %d",
                pendingUsers.size(), approvedUsers.size(), rejectedUsers.size());
    }
}

class JobSeekerPanel extends JPanel {
    // JobSeeker inner class 
    class JobSeeker {
        private String id;
        private String name;
        private String email;
        private String phone;
        private String resumePath;

        public JobSeeker(String id, String name, String email, String phone, String resumePath) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.resumePath = resumePath;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getResumePath() { return resumePath; }

        public void setName(String name) { this.name = name; }
        public void setEmail(String email) { this.email = email; }
        public void setPhone(String phone) { this.phone = phone; }
        public void setResumePath(String resumePath) { this.resumePath = resumePath; }
    }

    // Job inner class 
    class JobListing {
        String title;
        String company;
        String description;
        java.util.List<String> requirements;

        JobListing(String title, String company, String description, java.util.List<String> requirements) {
            this.title = title;
            this.company = company;
            this.description = description;
            this.requirements = requirements;
        }
    }

    // Application inner class
    class JobApplication {
        JobListing job;
        String status;

        JobApplication(JobListing job, String status) {
            this.job = job;
            this.status = status;
        }
    }

    private JobSeeker currentSeeker;
    private java.util.List<JobListing> jobs;
    private java.util.List<JobApplication> applications;
    private List<Application> sharedApplications;
    private Color coffeeColor = new Color(111, 78, 55);
    private Color lightCoffee = new Color(210, 180, 140);
    private Runnable onBack;

    public JobSeekerPanel(List<Application> sharedApplications, Runnable onBack) {
        this.onBack = onBack;
        this.sharedApplications = sharedApplications;
        
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        currentSeeker = new JobSeeker("JS001", "Job Seeker", "jobseeker@example.com", "1234567890", "");
        jobs = new ArrayList<>();
        applications = new ArrayList<>();
        loadJobs();

        // Header
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Welcome, " + currentSeeker.getName(), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        JLabel subLabel = new JLabel("Your Career Starts Here", JLabel.CENTER);
        subLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        subLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        headerPanel.add(subLabel);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 15, 15));
        buttonPanel.setOpaque(false);

        String[] features = {
                "View / Edit Profile",
                "Upload Resume",
                "Browse Jobs",
                "Apply to Job",
                "View Application Status",
                "Back"
        };

        for (String feature : features) {
            JButton btn = new JButton(feature);
            btn.setBackground(coffeeColor.darker());
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            btn.addActionListener(e -> handleAction(feature));
            buttonPanel.add(btn);
        }

        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, lightCoffee, getWidth(), getHeight(), coffeeColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void loadJobs() {
        jobs.add(new JobListing("Software Engineer", "Tech Corp",
                "Develop and maintain software applications.",
                Arrays.asList("Java", "OOP concepts", "Problem solving")));
        jobs.add(new JobListing("Data Analyst", "DataWorks",
                "Analyze and interpret complex data sets.",
                Arrays.asList("Excel", "SQL", "Critical thinking")));
        jobs.add(new JobListing("Web Developer", "Webify",
                "Build and maintain websites and web apps.",
                Arrays.asList("HTML", "CSS", "JavaScript", "React")));
        jobs.add(new JobListing("Mobile App Developer", "AppWorld",
                "Create mobile applications for Android/iOS.",
                Arrays.asList("Java", "Kotlin", "Flutter")));
        jobs.add(new JobListing("Content Writer", "WriteNow",
                "Create engaging and SEO-friendly content.",
                Arrays.asList("Creativity", "SEO knowledge", "Grammar skills")));
    }

    private void handleAction(String action) {
        switch (action) {
            case "View / Edit Profile":
                JPanel profilePanel = new JPanel();
                profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
                profilePanel.setBackground(new Color(245, 222, 179));
                profilePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

                JLabel title = new JLabel("Edit Profile");
                title.setFont(new Font("Arial", Font.BOLD, 18));
                title.setAlignmentX(Component.CENTER_ALIGNMENT);
                profilePanel.add(title);
                profilePanel.add(Box.createVerticalStrut(10));

                JTextField nameField = new JTextField(currentSeeker.getName(), 15);
                JTextField emailField = new JTextField(currentSeeker.getEmail(), 15);
                JTextField phoneField = new JTextField(currentSeeker.getPhone(), 15);

                profilePanel.add(new JLabel("Name:"));
                profilePanel.add(nameField);
                profilePanel.add(Box.createVerticalStrut(8));
                profilePanel.add(new JLabel("Email:"));
                profilePanel.add(emailField);
                profilePanel.add(Box.createVerticalStrut(8));
                profilePanel.add(new JLabel("Phone:"));
                profilePanel.add(phoneField);

                int result = JOptionPane.showConfirmDialog(
                        SwingUtilities.getWindowAncestor(this),
                        profilePanel,
                        "View / Edit Profile",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    currentSeeker.setName(nameField.getText());
                    currentSeeker.setEmail(emailField.getText());
                    currentSeeker.setPhone(phoneField.getText());
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Profile updated successfully!");
                }
                break;

            case "Upload Resume":
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(SwingUtilities.getWindowAncestor(this));
                if (option == JFileChooser.APPROVE_OPTION) {
                    currentSeeker.setResumePath(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Resume uploaded successfully!");
                }
                break;

            case "Browse Jobs":
                String[] jobTitles = jobs.stream().map(j -> j.title).toArray(String[]::new);
                String selectedJob = (String) JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this), "Select a job:", "Browse Jobs",
                        JOptionPane.QUESTION_MESSAGE, null, jobTitles, jobTitles[0]);
                if (selectedJob != null) {
                    JobListing job = jobs.stream().filter(j -> j.title.equals(selectedJob)).findFirst().orElse(null);
                    if (job != null) {
                        StringBuilder details = new StringBuilder();
                        details.append("Job Title: ").append(job.title).append("\n");
                        details.append("Company: ").append(job.company).append("\n");
                        details.append("Description: ").append(job.description).append("\n");
                        details.append("Requirements:\n");
                        for (String req : job.requirements) {
                            details.append(" - ").append(req).append("\n");
                        }
                        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), details.toString());
                    }
                }
                break;

            case "Apply to Job":
                String[] applyTitles = jobs.stream().map(j -> j.title).toArray(String[]::new);
                String jobToApply = (String) JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Select a job to apply:", "Apply to Job",
                    JOptionPane.QUESTION_MESSAGE, null, applyTitles, applyTitles[0]);
                if (jobToApply != null) {
                    JobListing job = jobs.stream().filter(j -> j.title.equals(jobToApply)).findFirst().orElse(null);
        if (job != null) {
            sharedApplications.add(new Application(currentSeeker.getName(), job.title));
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "Applied to " + job.title + " successfully!");
        }
    }
    break;
          case "View Application Status":
    List<Application> seekerApps = sharedApplications.stream()
        .filter(a -> a.getCandidateName().equals(currentSeeker.getName()))
        .toList();

    if (seekerApps.isEmpty()) {
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "No applications yet.");
    } else {
        StringBuilder statusList = new StringBuilder();
        for (Application app : seekerApps) {
            statusList.append(app.getJobTitle()).append(" - ").append(app.getStatus()).append("\n");
        }
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), statusList.toString());
    }
    break;


            case "Back":
                if (onBack != null) {
                    onBack.run();
                }
                break;
        }
    }
}

// Job class (for Employer panel)
class Job implements java.io.Serializable {
    private String title;
    private String description;
    private String location;
    private double salary;

    public Job(String title, String description, String location, double salary) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public double getSalary() { return salary; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
     public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return title + " | " + location + " | " + salary;
    }
}

// Application class
class Application implements java.io.Serializable {
    private String candidateName;
    private String jobTitle;
    private String status; // Pending, Accepted, Rejected

    public Application(String candidateName, String jobTitle) {
        this.candidateName = candidateName;
        this.jobTitle = jobTitle;
        this.status = "Pending";
    }

    // Getters and setters
    public String getCandidateName() { return candidateName; }
    public String getJobTitle() { return jobTitle; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return candidateName + " applied for " + jobTitle + " (" + status + ")";
    }
}


class StorageHelper {

    public static <T> void saveData(ArrayList<T> list, String fileName) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> loadData(String fileName) {
        java.io.File file = new java.io.File(fileName);
        if (!file.exists()) return new ArrayList<>();
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(fileName))) {
            return (ArrayList<T>) ois.readObject();
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
