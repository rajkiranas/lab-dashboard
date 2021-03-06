/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quick.ui.exam;

/**
 * DISCLAIMER
 * 
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 * 
 * @author jouni@vaadin.com
 * 
 */



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quick.bean.ExamBean;
import com.quick.bean.Userprofile;
import com.quick.table.MyDashBoardDataProvider;

import com.vaadin.data.Property;
import com.quick.data.Generator;
import com.quick.global.GlobalConstants;
import com.quick.utilities.UIUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class StudentExam extends VerticalLayout implements View  {

        private TextField subtxt ;
        private TextField markstxt;
        private TextField scoretxt;
        private TextField questionstxt;
        private Button startExamBtn;;
        private HorizontalLayout row1;
        private HorizontalLayout row2;
        private CssLayout examsummaryPannel;
        private String widthForExamDetailsFormFields="125px";
        private CssLayout cssExamScoreComparisonLayout;
        
    Table t;
    MyDashBoardDataProvider boardDataProvider = new MyDashBoardDataProvider();
    private List<ExamBean> selectedExam;

   
    /**
     * @return the selectedExam
     */
    public List<ExamBean> getSelectedExam() {
        return selectedExam;
    }

    /**
     * @param selectedExam the selectedExam to set
     */
    public void setSelectedExam(List<ExamBean> selectedExam) {
        this.selectedExam = selectedExam;
    }
    
    private static final String Subject ="Subject";
    private static final String Marks ="Total marks";
    private static final String Questions ="Questions";
    private static final String Score ="Obtained marks";
    public StudentExam() {
        subtxt =new TextField();
        subtxt.setImmediate(true);
        subtxt.setCaption(Subject);
        subtxt.setWidth(widthForExamDetailsFormFields);
        
        markstxt =new TextField();
        markstxt.setCaption(Marks);
        markstxt.setImmediate(true);
        markstxt.setWidth(widthForExamDetailsFormFields);
        
        scoretxt =new TextField();
        scoretxt.setCaption(Score);
        scoretxt.setImmediate(true);
        scoretxt.setWidth(widthForExamDetailsFormFields);
        
        questionstxt =new TextField();
        questionstxt.setCaption(Questions);
        questionstxt.setImmediate(true);
        questionstxt.setWidth(widthForExamDetailsFormFields);
        
        startExamBtn = new Button(GlobalConstants.startExam);
        startExamBtn.setImmediate(true);
        startExamBtn.addStyleName(GlobalConstants.default_style);
        startExamBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                startOrViewtExam();
            }

            
        });
       // addStyleName("dashboard-view");
    }
    
    private static final String Student_Exams="Exams";
    private int barchartAdded=0;
    private void buildUi(){
        
        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setSpacing(true);
        top.setMargin(true);
        top.addStyleName("toolbar");
        top.addStyleName("lightBackgroundForDashboardActivity");
        top.addStyleName("lightGrayFourSideBorder");
        
        addComponent(top);
        final Label title = new Label(Student_Exams);
        title.setSizeUndefined();
        title.addStyleName("h1");
        top.addComponent(title);
        top.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        top.setExpandRatio(title, 1);

        Button notify = new Button("2");
        notify.setDescription("Notifications (2 unread)");
        // notify.addStyleName("borderless");
        notify.addStyleName("notifications");
        notify.addStyleName("unread");
        notify.addStyleName("icon-only");
        notify.addStyleName("icon-bell");
        notify.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                //((DashboardUI) getUI()).clearDashboardButtonBadge();
                event.getButton().removeStyleName("unread");
                event.getButton().setDescription("Notifications");

                if (notifications != null && notifications.getUI() != null)
                    notifications.close();
                else {
                    buildNotifications(event);
                    getUI().addWindow(notifications);
                    notifications.focus();
                    ((CssLayout) getUI().getContent())
                            .addLayoutClickListener(new LayoutClickListener() {
                                @Override
                                public void layoutClick(LayoutClickEvent event) {
                                    notifications.close();
                                    ((CssLayout) getUI().getContent())
                                            .removeLayoutClickListener(this);
                                }
                            });
                }

            }
        });
//        top.addComponent(notify);
//        top.setComponentAlignment(notify, Alignment.MIDDLE_LEFT);

        Button edit = new Button();
        edit.addStyleName("icon-edit");
        edit.addStyleName("icon-only");
        //top.addComponent(edit);
        edit.setDescription("Edit Dashboard");
        edit.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                final Window w = new Window("Edit Dashboard");

                w.setModal(true);
                w.setClosable(false);
                w.setResizable(false);
                w.addStyleName("edit-dashboard");

                getUI().addWindow(w);

                w.setContent(new VerticalLayout() {
                    TextField name = new TextField("Dashboard Name");
                    {
                        addComponent(new FormLayout() {
                            {
                                setSizeUndefined();
                                setMargin(true);
                                name.setValue(title.getValue());
                                addComponent(name);
                                name.focus();
                                name.selectAll();
                            }
                        });

                        addComponent(new HorizontalLayout() {
                            {
                                setMargin(true);
                                setSpacing(true);
                                addStyleName("footer");
                                setWidth("100%");

                                Button cancel = new Button("Cancel");
                                cancel.addClickListener(new ClickListener() {
                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        w.close();
                                    }
                                });
                                cancel.setClickShortcut(KeyCode.ESCAPE, null);
                                addComponent(cancel);
                                setExpandRatio(cancel, 1);
                                setComponentAlignment(cancel,
                                        Alignment.TOP_RIGHT);

                                Button ok = new Button("Save");
                                ok.addStyleName("wide");
                                ok.addStyleName("default");
                                ok.addClickListener(new ClickListener() {
                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        title.setValue(name.getValue());
                                        w.close();
                                    }
                                });
                                ok.setClickShortcut(KeyCode.ENTER, null);
                                addComponent(ok);
                            }
                        });

                    }
                });

            }
        });
        //top.setComponentAlignment(edit, Alignment.MIDDLE_LEFT);
        top.addComponent(startExamBtn);
        top.setComponentAlignment(startExamBtn, Alignment.MIDDLE_LEFT);
        
        
        
        row1 = new HorizontalLayout();
        row1.setSizeFull();
        row1.setMargin(new MarginInfo(true, true, false, true));
        row1.setSpacing(true);
        addComponent(row1);
        setExpandRatio(row1, 2); 

        Userprofile userprofile = (Userprofile) getSession().getAttribute(GlobalConstants.CurrentUserProfile);
        StudentExamDataProvider provider = new StudentExamDataProvider();
        Table examlistTbl = provider.getStudentExamList(getExamList(userprofile.getStd(),userprofile.getDiv()));
        examlistTbl.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
               ExamBean eb = (ExamBean) event.getProperty().getValue(); 
               if(eb!=null)
                {
                    setSelectedExam(getSelectedExamDetailsById(eb.getExamId()));
                    System.out.println("//////////" + getSelectedExam().get(0).getResponseDt());
                    updateExamDetails();
                    getExamDetailsLayout(getSelectedExam());
                    updateExamSummary();
                    decidevisibilityAndCaptionOfStartExamBtn(eb);
                    if (barchartAdded == 1) {
                        getExamScoreComparisonBarChart();
                    }
                }
            }
        });
        
        examlistTbl.select(examlistTbl.firstItemId());
        row1.addComponent(UIUtils.createPanel(examlistTbl));
        
        //row1.addComponent(UIUtils.createPanel(StudentExamDataProvider.getMyExamPieChart(getSubjectWiseAvgPerformanceList(),getSubwiseAvgScoreForStud())));
        
        getExamScoreComparisonBarChart();
        barchartAdded=1;
        

        row2 = new HorizontalLayout();
        row2.setMargin(true);
        row2.setSizeFull();
        row2.setSpacing(true);
        addComponent(row2);
        setExpandRatio(row2, 2);

        //////////////////////////////////////////////////////////////////////////////////////
        //// anonymous table for format property value ////
        //////////////////////////////////////////////////////////////////////////////////////////
//        t = new Table() {
//            @Override
//            protected String formatPropertyValue(Object rowId, Object colId,
//                    Property<?> property) {
//                if (colId.equals("Revenue")) {
//                    if (property != null && property.getValue() != null) {
//                        Double r = (Double) property.getValue();
//                        String ret = new DecimalFormat("#.##").format(r);
//                        return "$" + ret;
//                    } else {
//                        return "";
//                    }
//                }
//                return super.formatPropertyValue(rowId, colId, property);
//            }
//        };
//        t.setCaption("Top 10 Titles by Revenue");
//
//        t.setWidth("100%");
//        t.setPageLength(0);
//        t.addStyleName("plain");
//        t.addStyleName("borderless");
//        t.setSortEnabled(false);
//        t.setColumnAlignment("Revenue", Align.RIGHT);
//        t.setRowHeaderMode(RowHeaderMode.INDEX);

        ///Component examDetails=UIUtils.createPanel(getExamDetailsAndComparisonBarChartLayout());
        
        getExamDetailsLayout(null);
        

        Component examChart = getExamDetailsPieChart();
        examsummaryPannel=UIUtils.createPanel(UIUtils.getTabSheetPaneView(examChart));
        row2.addComponent(examsummaryPannel);
        
        
//        row2.setComponentAlignment(examDetails,Alignment.TOP_CENTER);
//        row2.setExpandRatio(examDetails, 1);     
        
        
//        row2.setComponentAlignment(examsummaryPannel,Alignment.MIDDLE_CENTER);
//        row2.setExpandRatio(examsummaryPannel, 2.5f);


    }
    
    CssLayout cssLayoutFormAndBarGraph;
    private void getExamDetailsLayout(List<ExamBean> ebList)
    {
         //HorizontalLayout examDetailsForm  = new HorizontalLayout();

        if(row2!=null)
        {
            if(cssLayoutFormAndBarGraph!=null)
            {
                row2.removeComponent(cssLayoutFormAndBarGraph);            
            }
            
            Component examDeatils = getSelectedExamDetailsForm(ebList);
            
            //examDetailsFormAndBarGraphLayout.addComponent(barChart);
//            examDetailsForm.addComponent(examDeatils);
//            examDetailsForm.setComponentAlignment(examDeatils,Alignment.TOP_CENTER);
//            examDetailsForm.setExpandRatio(examDeatils, 1);     

//            examDetailsFormAndBarGraphLayout.setComponentAlignment(barChart,Alignment.MIDDLE_CENTER);
//            examDetailsFormAndBarGraphLayout.setExpandRatio(barChart, 2.5f);

            VerticalLayout v = new VerticalLayout();
            v.setSizeFull();
            v.addComponent(examDeatils);
            v.setComponentAlignment(examDeatils,Alignment.MIDDLE_CENTER);
            v.setExpandRatio(examDeatils, 2.5f);
            
            if(ebList!=null && ebList.size()>0)
            {
                HorizontalLayout h = new HorizontalLayout();
                h.setSizeFull();
                Label contestLine=new Label("<b>"+ebList.get(0).getContestLine()+"</b>", ContentMode.HTML);
                h.addComponent(contestLine);
                h.setComponentAlignment(contestLine, Alignment.MIDDLE_CENTER);
                h.addStyleName("YellowBackground");
                
                v.addComponent(h);
                v.setComponentAlignment(h,Alignment.MIDDLE_CENTER);
                v.setExpandRatio(h, 0.35f);
            }
            
            cssLayoutFormAndBarGraph=UIUtils.createPanel(v);

            row2.addComponent(cssLayoutFormAndBarGraph);
        }
        
        //return examDetailsForm;
    }
   
    /* public  Component getSelectedExamDetailsForm(List<ExamBean> ebList) {
        FormLayout formLayout = new FormLayout();
        formLayout.setCaption("Exam details");
        formLayout.setMargin(true);
      
        formLayout.addComponent(subtxt);
        formLayout.addComponent(questionstxt);
        formLayout.addComponent(markstxt);
        formLayout.addComponent(scoretxt);
        
        return formLayout;
    } */
    
    public  Component getSelectedExamDetailsForm(List<ExamBean> ebList) {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setMargin(false);
        formLayout.setSizeFull();
        //formLayout.setCaption("Exam details");
        
        /* formLayout.addComponent(subtxt);
        formLayout.addComponent(questionstxt);
        formLayout.addComponent(markstxt);
        formLayout.addComponent(scoretxt); */
        
        Label caption = new Label("Exam details");
        caption.setSizeFull();
        caption.setStyleName("brownBackgroundColor");
        formLayout.addComponent(caption); 
        formLayout.setComponentAlignment(caption, Alignment.MIDDLE_LEFT);
        formLayout.setExpandRatio(caption, 0.35f);
        
        HorizontalLayout adj = new HorizontalLayout();
        adj.setSizeFull();
        Label emptyLbl=new Label(GlobalConstants.emptyString);
        adj.addComponent(emptyLbl);
        adj.setExpandRatio(emptyLbl, 1);
        
        int iPassingMarks=0;
        String strExamName=GlobalConstants.HYPHEN;
        int iMarksPerQuestion=0;
        
        if(ebList!=null && ebList.size()>0)
            {
                iPassingMarks=ebList.get(0).getPassingMarks();
                strExamName=ebList.get(0).getExName();
                iMarksPerQuestion=ebList.get(0).getMarksPerQuestion();
            }
        
        
        
       
        Label lbl1 = new Label("<h5><b>Exam Name: "+strExamName+"</b></h5>" +
                            "<h5><b>No. of Questions: "+questionstxt+"</b></h5>" + 
                            "<h5><b>Passing marks: "+iPassingMarks+"</b></h5>" +
                            "<h5><b>Obtained marks: "+scoretxt+"</b></h5>", ContentMode.HTML);
        
        lbl1.setSizeFull();
        adj.addComponent(lbl1);
        adj.setExpandRatio(lbl1, 2);
        
        Label lbl2 = new Label("<h5><b>Subject: "+subtxt+"</b></h5>" +
                            
                            "<h5><b>Total marks: "+markstxt+"</b></h5>" +
                            
                            "<h5><b>Marks/Question: "+iMarksPerQuestion+"</b></h5>", ContentMode.HTML);
        
        lbl2.setSizeFull();
        adj.addComponent(lbl2);
        adj.setExpandRatio(lbl2, 2);
        
        formLayout.addComponent(adj); 
        formLayout.setExpandRatio(adj, 2.75f);
        
        return formLayout;
    }
    
//    private CssLayout createPanel(Component content) {
//        CssLayout panel = new CssLayout();
//        panel.addStyleName("layout-panel");
//        panel.setSizeFull();
//
//        Button configure = new Button();
//        configure.addStyleName("configure");
//        configure.addStyleName("icon-cog");
//        configure.addStyleName("icon-only");
//        configure.addStyleName("borderless");
//        configure.setDescription("Configure");
//        configure.addStyleName("small");
//        configure.addClickListener(new ClickListener() {
//            @Override
//            public void buttonClick(ClickEvent event) {
//                Notification.show("Not implemented in this demo");
//            }
//        });
//        panel.addComponent(configure);
//        
//
//        panel.addComponent(content);
//        return panel;
//    }

    @Override
    public void enter(ViewChangeEvent event) {
         setSizeFull();
           buildUi();
//        DataProvider dataProvider = ((DashboardUI) getUI()).dataProvider;
//        t.setContainerDataSource(dataProvider.getRevenueByTitle());
    }

    Window notifications;

    private void buildNotifications(ClickEvent event) {
        notifications = new Window("Notifications");
        VerticalLayout l = new VerticalLayout();
        l.setMargin(true);
        l.setSpacing(true);
        notifications.setContent(l);
        notifications.setWidth("300px");
        notifications.addStyleName("notifications");
        notifications.setClosable(false);
        notifications.setResizable(false);
        notifications.setDraggable(false);
        notifications.setPositionX(event.getClientX() - event.getRelativeX());
        notifications.setPositionY(event.getClientY() - event.getRelativeY());
        notifications.setCloseShortcut(KeyCode.ESCAPE, null);

        Label label = new Label(
                "<hr><b>"
                        + Generator.randomFirstName()
                        + " "
                        + Generator.randomLastName()
                        + " created a new report</b><br><span>25 minutes ago</span><br>"
                        + Generator.randomText(18), ContentMode.HTML);
        l.addComponent(label);

        label = new Label("<hr><b>" + Generator.randomFirstName() + " "
                + Generator.randomLastName()
                + " changed the schedule</b><br><span>2 days ago</span><br>"
                + Generator.randomText(10), ContentMode.HTML);
        l.addComponent(label);
    }
    
    
    
     private void updateExamDetails() {
       updateSelectedExamDetailsPanel();         
     }

    private void updateExamSummary()
    {
       if(row2!=null){
           
        row2.removeComponent(examsummaryPannel);
        Component examChart = getExamDetailsPieChart();
        examsummaryPannel = UIUtils.createPanel(UIUtils.getTabSheetPaneView(examChart));
        row2.addComponent(examsummaryPannel);

       }
    }
     
     private void updateSelectedExamDetailsPanel() {
        ExamBean eb = getSelectedExam().get(0);
      
        subtxt.setReadOnly(false);
        markstxt.setReadOnly(false);
        scoretxt.setReadOnly(false);
        questionstxt.setReadOnly(false);
        
        subtxt.setValue(eb.getSub());
       
        markstxt.setValue(GlobalConstants.emptyString+eb.getTotalMarks());
        
        scoretxt.setValue(GlobalConstants.emptyString+eb.getTotalObtMarksObj());
        
        questionstxt.setValue(GlobalConstants.emptyString+eb.getNoOfQuestions());
        
        subtxt.setReadOnly(true);
        markstxt.setReadOnly(true);
        scoretxt.setReadOnly(true);
        questionstxt.setReadOnly(true);
    }
    
      private void startOrViewtExam() {
          
       ExamBean eb = getSelectedExam().get(0);
       Userprofile userprofile = (Userprofile) getSession().getAttribute(GlobalConstants.CurrentUserProfile);
       // startExamBtn.getCaption() is used to decide between start exam and view exam
       CreateStudExamPopup examPopup = new CreateStudExamPopup(this,eb,startExamBtn.getCaption(),userprofile.getUsername());
       UI.getCurrent().addWindow(examPopup);
       examPopup.focus();
      }
    
    public  List<ExamBean> getExamList(String std,String div) {
       
         List<ExamBean> examList = null;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(GlobalConstants.getProperty(GlobalConstants.GET_EXAM_LIST));
            //String input = "{\"userName\":\"raj\",\"password\":\"FadeToBlack\"}";
            JSONObject inputJson = new JSONObject();
             try
             {           
                Userprofile userprofile = (Userprofile) getSession().getAttribute(GlobalConstants.CurrentUserProfile);
                inputJson.put("std", std);
                inputJson.put("div", div);
                inputJson.put("username",userprofile.getUsername());
             }catch(Exception ex){
                 ex.printStackTrace();
             }
            
            ClientResponse response = webResource.type(GlobalConstants.application_json).post(ClientResponse.class, inputJson);
            
            JSONObject outNObject = null;
            String output = response.getEntity(String.class);
            outNObject = new JSONObject(output);

            Type listType = new TypeToken<ArrayList<ExamBean>>() {
            }.getType();
            
            examList= new Gson().fromJson(outNObject.getString(GlobalConstants.EXAMLIST), listType);
            
            subjectWiseAvgPerformanceList = new Gson().fromJson(outNObject.getString(GlobalConstants.subjectWiseAvgPerformance), listType);
            subwiseAvgScoreForStud = new Gson().fromJson(outNObject.getString(GlobalConstants.subwiseAvgScoreForStud), listType);
        } catch (JSONException ex) 
        {
            ex.printStackTrace();
          //  L.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return examList;            
    }
    
    private List<ExamBean> subjectWiseAvgPerformanceList;
    
    private List<ExamBean> subwiseAvgScoreForStud;
    
    private List<ExamBean> getSelectedExamDetailsById(int examId){
         List<ExamBean> selectedExamDetails = null;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(GlobalConstants.getProperty(GlobalConstants.GET_EXAM_DETAILS_BY_ID));
            //String input = "{\"userName\":\"raj\",\"password\":\"FadeToBlack\"}";
            JSONObject inputJson = new JSONObject();
             try{           
                inputJson.put("exmId", examId);
//                inputJson.put("div", "A-1");
             }catch(Exception ex)
             {
                 ex.printStackTrace();
                 
             }
            
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, inputJson);
            
            JSONObject outNObject = null;
            String output = response.getEntity(String.class);
            outNObject = new JSONObject(output);
            
            int status = Integer.parseInt(outNObject.getString(GlobalConstants.STATUS));
            
            if(status == GlobalConstants.NO)
            {
                Notification.show("Retrieving exam details failed", Notification.Type.WARNING_MESSAGE);
            }

            Type listType = new TypeToken<ArrayList<ExamBean>>() {
            }.getType();
            
            selectedExamDetails= new Gson().fromJson(outNObject.getString(GlobalConstants.EXAMLIST), listType);
        } catch (JSONException ex) {
          ex.printStackTrace();
        }
        return selectedExamDetails;
    }

    
    private static final String Absent="Absent";
    private static final String Fail="Fail";
    private static final String Passed="Passed";

   private Component getExamDetailsPieChart() {
        
        ExamBean eb = getSelectedExam().get(0);
        HashMap<String,Double> dataMap = new HashMap<String,Double>();
        
        dataMap.put(Absent, ((double) eb.getTotalStudents() - (double) eb.getAppearedStudents()));

        dataMap.put(Fail, (double) eb.getFailedStudents());

        dataMap.put(Passed, (double) eb.getPassedStudents());

        return CustomPieChart.createChart(dataMap,Passed,"Exam summary");
        
    }

    

    private void decidevisibilityAndCaptionOfStartExamBtn(ExamBean eb) {
        Date now = new Date();
        
        if (now.getTime() >= eb.getStartDt().getTime() && now.getTime() <= eb.getEndDt().getTime()) {
            if( getSelectedExam().get(0).getResponseDt()==null)
            {
                startExamBtn.setCaption(GlobalConstants.startExam);
            }
            else
            {
                startExamBtn.setCaption(GlobalConstants.viewExam);
            }
        }
        else if (now.getTime() > eb.getEndDt().getTime()) 
        {
            startExamBtn.setCaption(GlobalConstants.viewExam);
        } 
        else 
        {
            startExamBtn.setVisible(false);
        }
    }

    /**
     * @return the subjectWiseAvgPerformanceList
     */
    public List<ExamBean> getSubjectWiseAvgPerformanceList() {
        return subjectWiseAvgPerformanceList;
    }

    /**
     * @param subjectWiseAvgPerformanceList the subjectWiseAvgPerformanceList to set
     */
    public void setSubjectWiseAvgPerformanceList(List<ExamBean> subjectWiseAvgPerformanceList) {
        this.subjectWiseAvgPerformanceList = subjectWiseAvgPerformanceList;
    }

    /**
     * @return the subwiseAvgScoreForStud
     */
    public List<ExamBean> getSubwiseAvgScoreForStud() {
        return subwiseAvgScoreForStud;
    }

    /**
     * @param subwiseAvgScoreForStud the subwiseAvgScoreForStud to set
     */
    public void setSubwiseAvgScoreForStud(List<ExamBean> subwiseAvgScoreForStud) {
        this.subwiseAvgScoreForStud = subwiseAvgScoreForStud;
    }

    
    private void getExamScoreComparisonBarChart() 
    {
        if(cssExamScoreComparisonLayout!=null)
        {
            row1.removeComponent(cssExamScoreComparisonLayout);
        }
        
        String[] title = new String[] {"My Score","Avg Score","Top Score"};
        Number[] scores = new Number[] { getSelectedExam().get(0).getTotalObtMarksObj(),getSelectedExam().get(0).getExamAvgScore(), getSelectedExam().get(0).getExamTopScore()};
        Component barChart=UIUtils.getBarChart(title,scores,"Exam score comparison","Score","Marks","100%","100%");
        cssExamScoreComparisonLayout=UIUtils.createPanel(barChart);
        
        row1.addComponent(cssExamScoreComparisonLayout);
        //return barChart;
    }

}
