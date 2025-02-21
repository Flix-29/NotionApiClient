package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.database.databaseProperty.*;
import de.flix29.notionApiClient.model.database.databaseProperty.Number;

import java.util.Collections;
import java.util.List;

public class DatabaseContentTestdata {

    public static List<Property> emptyProperties() {
        return List.of(
                emailEmpty(),
                peopleEmpty(),
                lastEditedByEmpty(),
                phoneNumberEmpty(),
                checkboxEmpty(),
                urlEmpty(),
                relationEmpty(),
                lastEditedTimeEmpty(),
                dateEmpty(),
                rollupEmpty(),
                createdTimeEmpty(),
                buttonEmpty(),
                statusEmpty(),
                numberEmpty(),
                fileEmpty(),
                multiSelectEmpty(),
                createdByEmpty(),
                selectEmpty(),
                textEmpty(),
                formulaEmpty(),
                titleEmpty()
        );
    }

    private static Email emailEmpty() {
        return (Email) new Email()
                .setEmail(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static People peopleEmpty() {
        return (People) new People()
                .setPeople(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static LastEditedBy lastEditedByEmpty() {
        return (LastEditedBy) new LastEditedBy()
                .setUser(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static PhoneNumber phoneNumberEmpty() {
        return (PhoneNumber) new PhoneNumber()
                .setPhoneNumber(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Checkbox checkboxEmpty() {
        return (Checkbox) new Checkbox()
                .setChecked(false)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Url urlEmpty() {
        return (Url) new Url()
                .setUrl(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Relation relationEmpty() {
        return (Relation) new Relation()
                .setDatabaseId(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static LastEditedTime lastEditedTimeEmpty() {
        return (LastEditedTime) new LastEditedTime()
                .setLastEditedTime(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Date dateEmpty() {
        return (Date) new Date()
                .setStart(null)
                .setEnd(null)
                .setTimezone(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Rollup rollupEmpty() {
        return (Rollup) new Rollup()
                .setRelationPropertyName(null)
                .setRollupPropertyName(null)
                .setRelationPropertyId(null)
                .setRollupPropertyId(null)
                .setFunction(RollupFunction.PERCENT_NOT_EMPTY)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static CreatedTime createdTimeEmpty() {
        return (CreatedTime) new CreatedTime()
                .setCreatedTime(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Button buttonEmpty() {
        return (Button) new Button()
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Status statusEmpty() {
        return (Status) new Status()
                .setOptions(optionsEmpty())
                .setGroups(groupsEmpty())
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static List<StatusItem> optionsEmpty() {
        var option = new StatusItem(false)
                .setColor(Color.DEFAULT)
                .setId(null)
                .setName(null)
                .setDescription(null)
                .setOptionIds(Collections.emptyList());

        return List.of(option, option, option);
    }

    private static List<StatusItem> groupsEmpty() {
        var group = new StatusItem(true)
                .setColor(Color.DEFAULT)
                .setId(null)
                .setName(null)
                .setOptionIds(Collections.emptyList());

        return List.of(group, group, group);
    }

    private static Number numberEmpty() {
        return (Number) new Number()
                .setFormat(NumberFormat.NUMBER)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static File fileEmpty() {
        return (File) new File()
                .setFiles(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static MultiSelect multiSelectEmpty() {
        return (MultiSelect) new MultiSelect()
                .setOptions(selectOptionsEmpty())
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static List<SelectItem> selectOptionsEmpty() {
        var option = new SelectItem()
                .setColor(Color.DEFAULT)
                .setId(null)
                .setName(null)
                .setDescription(null);

        return List.of(option, option, option);
    }

    private static CreatedBy createdByEmpty() {
        return (CreatedBy) new CreatedBy()
                .setUser(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Select selectEmpty() {
        return (Select) new Select()
                .setOptions(selectOptionsEmpty())
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static RichText textEmpty() {
        return (RichText) new RichText()
                .setContent(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Formula formulaEmpty() {
        return (Formula) new Formula()
                .setExpression(null)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static Title titleEmpty() {
        return (Title) new Title()
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    public static List<Property> allProperties() {
        return List.of(
                emailAllSet(),
                peopleAllSet(),
                lastEditedByAllSet(),
                phoneNumberAllSet(),
                checkboxAllSet(),
                urlAllSet(),
                relationAllSet(),
                lastEditedTimeAllSet(),
                dateAllSet(),
                rollupAllSet(),
                createdTimeAllSet(),
                buttonAllSet(),
                statusAllSet(),
                numberAllSet(),
                fileAllSet(),
                multiSelectAllSet(),
                createdByAllSet(),
                selectAllSet(),
                textAllSet(),
                formulaAllSet(),
                titleAllSet()
        );
    }

    private static Email emailAllSet() {
        return (Email) new Email()
                .setEmail(null)
                .setId("%3AMwQ")
                .setName("Email")
                .setDescription("Email");
    }

    private static People peopleAllSet() {
        return (People) new People()
                .setPeople(null)
                .setId("%3AMwQ")
                .setName("Person")
                .setDescription("Person");
    }

    private static LastEditedBy lastEditedByAllSet() {
        return (LastEditedBy) new LastEditedBy()
                .setUser(null)
                .setId("%3AMwQ")
                .setName("Last edited by")
                .setDescription("Last edited by");
    }

    private static PhoneNumber phoneNumberAllSet() {
        return (PhoneNumber) new PhoneNumber()
                .setPhoneNumber(null)
                .setId("%3AMwQ")
                .setName("Phone")
                .setDescription("Phone");
    }

    private static Checkbox checkboxAllSet() {
        return (Checkbox) new Checkbox()
                .setChecked(false)
                .setId("%3AMwQ")
                .setName("Checkbox")
                .setDescription("Checkbox");
    }

    private static Url urlAllSet() {
        return (Url) new Url()
                .setUrl(null)
                .setId("%3AMwQ")
                .setName("URL")
                .setDescription("URL");
    }

    private static Relation relationAllSet() {
        return (Relation) new Relation()
                .setDatabaseId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setId("%3AMwQ")
                .setName("Related TestDB")
                .setDescription("Related TestDB");
    }

    private static LastEditedTime lastEditedTimeAllSet() {
        return (LastEditedTime) new LastEditedTime()
                .setLastEditedTime(null)
                .setId("%3AMwQ")
                .setName("Last edited time")
                .setDescription("Last edited time");
    }

    private static Date dateAllSet() {
        return (Date) new Date()
                .setStart(null)
                .setEnd(null)
                .setTimezone(null)
                .setId("%3AMwQ")
                .setName("Date")
                .setDescription("Date");
    }

    private static Rollup rollupAllSet() {
        return (Rollup) new Rollup()
                .setRelationPropertyName("Related TestDB")
                .setRollupPropertyName("Name")
                .setRelationPropertyId("MvMc")
                .setRollupPropertyId("title")
                .setFunction(RollupFunction.PERCENT_NOT_EMPTY)
                .setId("%3AMwQ")
                .setName("Rollup")
                .setDescription("Rollup");
    }

    private static CreatedTime createdTimeAllSet() {
        return (CreatedTime) new CreatedTime()
                .setCreatedTime(null)
                .setId("%3AMwQ")
                .setName("Created time")
                .setDescription("Created time");
    }

    private static Button buttonAllSet() {
        return (Button) new Button()
                .setId("%3AMwQ")
                .setName("Button")
                .setDescription("Button");
    }

    private static Status statusAllSet() {
        return (Status) new Status()
                .setOptions(optionsAllSet())
                .setGroups(groupsAllSet())
                .setId("%3AMwQ")
                .setName("Status")
                .setDescription("Status");
    }

    private static List<StatusItem> optionsAllSet() {
        var option1 = new StatusItem(false)
                .setColor(Color.DEFAULT)
                .setId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setName("Not started")
                .setDescription("Not started")
                .setOptionIds(Collections.emptyList());

        var option2 = new StatusItem(false)
                .setColor(Color.BLUE)
                .setId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setName("In progress")
                .setDescription("In progress")
                .setOptionIds(Collections.emptyList());

        var option3 = new StatusItem(false)
                .setColor(Color.GREEN)
                .setId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setName("Done")
                .setDescription("Done")
                .setOptionIds(Collections.emptyList());

        return List.of(option1, option2, option3);
    }

    private static List<StatusItem> groupsAllSet() {
        var group1 = new StatusItem(true)
                .setColor(Color.GRAY)
                .setId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setName("To-do")
                .setOptionIds(List.of("c6e92391-c829-42ea-9ac7-949106ed3916"));

        var group2 = new StatusItem(true)
                .setColor(Color.BLUE)
                .setId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setName("In progress")
                .setOptionIds(List.of("c6e92391-c829-42ea-9ac7-949106ed3916"));

        var group3 = new StatusItem(true)
                .setColor(Color.GREEN)
                .setId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setName("Complete")
                .setOptionIds(List.of("c6e92391-c829-42ea-9ac7-949106ed3916"));

        return List.of(group1, group2, group3);
    }

    private static Number numberAllSet() {
        return (Number) new Number()
                .setFormat(NumberFormat.NUMBER)
                .setId("%3AMwQ")
                .setName("Number")
                .setDescription("Number");
    }

    private static File fileAllSet() {
        return (File) new File()
                .setFiles(null)
                .setId("%3AMwQ")
                .setName("Files & media")
                .setDescription("Files & media");
    }

    private static MultiSelect multiSelectAllSet() {
        return (MultiSelect) new MultiSelect()
                .setOptions(selectOptionsAllSet())
                .setId("%3AMwQ")
                .setName("Multi-select")
                .setDescription("Multi-select");
    }

    private static List<SelectItem> selectOptionsAllSet() {
        var option1 = new SelectItem()
                .setColor(Color.ORANGE)
                .setId("%3AMwQ")
                .setName("Select3")
                .setDescription("Select3");

        var option2 = new SelectItem()
                .setColor(Color.DEFAULT)
                .setId("%3AMwQ")
                .setName("Select2")
                .setDescription("Select2");

        var option3 = new SelectItem()
                .setColor(Color.RED)
                .setId("%3AMwQ")
                .setName("Select1")
                .setDescription("Select1");

        return List.of(option1, option2, option3);
    }

    private static CreatedBy createdByAllSet() {
        return (CreatedBy) new CreatedBy()
                .setUser(null)
                .setId("%3AMwQ")
                .setName("Created by")
                .setDescription("Created by");
    }

    private static Select selectAllSet() {
        return (Select) new Select()
                .setOptions(selectOptionsAllSet())
                .setId("%3AMwQ")
                .setName("Select")
                .setDescription("Select");
    }

    private static RichText textAllSet() {
        return (RichText) new RichText()
                .setContent(null)
                .setId("%3AMwQ")
                .setName("Text")
                .setDescription("Text");
    }

    private static Formula formulaAllSet() {
        return (Formula) new Formula()
                .setExpression("if({{notion:block_property:Kjvm:00000000-0000-0000-0000-000000000000:277e10d0-4870-45dd-9b60-8ca05e221089}}, \"ja\", \"nein\")")
                .setId("%3AMwQ")
                .setName("Formula")
                .setDescription("Formula");
    }

    private static Title titleAllSet() {
        return (Title) new Title()
                .setId("%3AMwQ")
                .setName("Name")
                .setDescription("Name");
    }
}
