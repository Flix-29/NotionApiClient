package de.flix29.notionApiClient.testdata;

import de.flix29.notionApiClient.model.Color;
import de.flix29.notionApiClient.model.FileType;
import de.flix29.notionApiClient.model.database.databaseProperty.*;
import de.flix29.notionApiClient.model.database.databaseProperty.Number;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

public class PagePropertiesTestdata {

    private static final String ID = "%3AMwQ";
    private static final OffsetDateTime dateTime = OffsetDateTime.parse("2025-01-01T12:00:00.000Z");

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
                .setName("Email")
                .setDescription(null);
    }

    private static People peopleEmpty() {
        return (People) new People()
                .setPeople(List.of(UserTestdata.peopleEmpty()))
                .setId(null)
                .setName("Person")
                .setDescription(null);
    }

    private static LastEditedBy lastEditedByEmpty() {
        return (LastEditedBy) new LastEditedBy()
                .setUser(UserTestdata.peopleEmpty())
                .setId(null)
                .setName("Last edited by")
                .setDescription(null);
    }

    private static PhoneNumber phoneNumberEmpty() {
        return (PhoneNumber) new PhoneNumber()
                .setPhoneNumber(null)
                .setId(null)
                .setName("Phone")
                .setDescription(null);
    }

    private static Checkbox checkboxEmpty() {
        return (Checkbox) new Checkbox()
                .setChecked(false)
                .setId(null)
                .setName("Checkbox")
                .setDescription(null);
    }

    private static Url urlEmpty() {
        return (Url) new Url()
                .setUrl(null)
                .setId(null)
                .setName("URL")
                .setDescription(null);
    }

    private static Relation relationEmpty() {
        return (Relation) new Relation()
                .setDatabaseId(null)
                .setRelatedPageIds(Collections.emptyList())
                .setId(null)
                .setName("Related TestDB")
                .setDescription(null);
    }

    private static LastEditedTime lastEditedTimeEmpty() {
        return (LastEditedTime) new LastEditedTime()
                .setLastEditedTime(null)
                .setId(null)
                .setName("Last edited time")
                .setDescription(null);
    }

    private static Date dateEmpty() {
        return (Date) new Date()
                .setStart(null)
                .setEnd(null)
                .setTimezone(null)
                .setId(null)
                .setName("Date")
                .setDescription(null);
    }

    private static Rollup rollupEmpty() {
        return (Rollup) new Rollup()
                .setRelationPropertyName(null)
                .setRollupPropertyName(null)
                .setRelationPropertyId(null)
                .setRollupPropertyId(null)
                .setFunction(null)
                .setId(null)
                .setName("Rollup")
                .setDescription(null);
    }

    private static CreatedTime createdTimeEmpty() {
        return (CreatedTime) new CreatedTime()
                .setCreatedTime(null)
                .setId(null)
                .setName("Created time")
                .setDescription(null);
    }

    private static Button buttonEmpty() {
        return (Button) new Button()
                .setId(null)
                .setName("Button")
                .setDescription(null);
    }

    private static Status statusEmpty() {
        return (Status) new Status()
                .setSelectedOption(optionEmpty())
                .setId(null)
                .setName("Status")
                .setDescription(null);
    }

    private static StatusItem optionEmpty() {
        return new StatusItem(false)
                .setColor(Color.DEFAULT)
                .setId(null)
                .setName(null)
                .setDescription(null)
                .setOptionIds(null);
    }

    private static de.flix29.notionApiClient.model.database.databaseProperty.Number numberEmpty() {
        return (de.flix29.notionApiClient.model.database.databaseProperty.Number) new de.flix29.notionApiClient.model.database.databaseProperty.Number()
                .setFormat(null)
                .setId(null)
                .setName("Number")
                .setDescription(null);
    }

    private static File fileEmpty() {
        return (File) new File()
                .setFiles(List.of(IconTestdata.fileEmpty()))
                .setId(null)
                .setName("Files & media")
                .setDescription(null);
    }

    private static MultiSelect multiSelectEmpty() {
        return (MultiSelect) new MultiSelect()
                .setSelected(List.of(selectOptionsEmpty()))
                .setId(null)
                .setName("Multi-select")
                .setDescription(null);
    }

    private static SelectItem selectOptionsEmpty() {
        return new SelectItem()
                .setColor(Color.DEFAULT)
                .setId(null)
                .setName(null)
                .setDescription(null);
    }

    private static CreatedBy createdByEmpty() {
        return (CreatedBy) new CreatedBy()
                .setUser(UserTestdata.peopleEmpty())
                .setId(null)
                .setName("Created by")
                .setDescription(null);
    }

    private static Select selectEmpty() {
        return (Select) new Select()
                .setSelected(selectOptionsEmpty())
                .setId(null)
                .setName("Select")
                .setDescription(null);
    }

    private static RichText textEmpty() {
        return (RichText) new RichText()
                .setContent(List.of(RichTextTestdata.richTextTextEmpty()))
                .setId(null)
                .setName("Text")
                .setDescription(null);
    }

    private static Formula formulaEmpty() {
        return (Formula) new Formula()
                .setExpression(null)
                .setId(null)
                .setName("Formula")
                .setDescription(null);
    }

    private static Title titleEmpty() {
        return (Title) new Title()
                .setTitle(List.of(RichTextTestdata.richTextTextEmpty()))
                .setId(null)
                .setName("Name")
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
                .setEmail("notion@mail.com")
                .setId(ID)
                .setName("Email");
    }

    private static People peopleAllSet() {
        return (People) new People()
                .setPeople(List.of(UserTestdata.peopleAllSet()))
                .setId(ID)
                .setName("Person");
    }

    private static LastEditedBy lastEditedByAllSet() {
        return (LastEditedBy) new LastEditedBy()
                .setUser(UserTestdata.peopleAllSet())
                .setId(ID)
                .setName("Last edited by");
    }

    private static PhoneNumber phoneNumberAllSet() {
        return (PhoneNumber) new PhoneNumber()
                .setPhoneNumber("+123 456789")
                .setId(ID)
                .setName("Phone");
    }

    private static Checkbox checkboxAllSet() {
        return (Checkbox) new Checkbox()
                .setChecked(true)
                .setId(ID)
                .setName("Checkbox");
    }

    private static Url urlAllSet() {
        return (Url) new Url()
                .setUrl("www.notion.so")
                .setId(ID)
                .setName("URL");
    }

    private static Relation relationAllSet() {
        return (Relation) new Relation()
                .setRelatedPageIds(List.of("c6e92391-c829-42ea-9ac7-949106ed3916"))
                .setId(ID)
                .setName("Related TestDB");
    }

    private static LastEditedTime lastEditedTimeAllSet() {
        return (LastEditedTime) new LastEditedTime()
                .setLastEditedTime(dateTime)
                .setId(ID)
                .setName("Last edited time");
    }

    private static Date dateAllSet() {
        return (Date) new Date()
                .setStart(dateTime)
                .setEnd(dateTime)
                .setTimezone("Europe/Berlin")
                .setId(ID)
                .setName("Date");
    }

    private static Rollup rollupAllSet() {
        return (Rollup) new Rollup()
                .setFunction(RollupFunction.PERCENT_NOT_EMPTY)
                .setType(RollupType.NUMBER)
                .setValue("1")
                .setId(ID)
                .setName("Rollup");
    }

    private static CreatedTime createdTimeAllSet() {
        return (CreatedTime) new CreatedTime()
                .setCreatedTime(dateTime)
                .setId(ID)
                .setName("Created time");
    }

    private static Button buttonAllSet() {
        return (Button) new Button()
                .setId(ID)
                .setName("Button");
    }

    private static Status statusAllSet() {
        return (Status) new Status()
                .setSelectedOption(selectedOptionAllSet())
                .setId(ID)
                .setName("Status");
    }

    private static StatusItem selectedOptionAllSet() {
        return new StatusItem(false)
                .setColor(Color.BLUE)
                .setId("c6e92391-c829-42ea-9ac7-949106ed3916")
                .setName("In progress");
    }

    private static Number numberAllSet() {
        return (Number) new Number()
                .setNumber(10)
                .setId(ID)
                .setName("Number");
    }

    private static File fileAllSet() {
        return (File) new File()
                .setFiles(List.of(new de.flix29.notionApiClient.model.File()
                        .setType(FileType.FILE)
                        .setUrl("https://www.notion.so")
                        .setExpirationTime(dateTime)))
                .setId(ID)
                .setName("Files & media");
    }

    private static MultiSelect multiSelectAllSet() {
        return (MultiSelect) new MultiSelect()
                .setSelected(selectOptionsAllSet())
                .setId(ID)
                .setName("Multi-select");
    }

    private static List<SelectItem> selectOptionsAllSet() {
        var option1 = new SelectItem()
                .setColor(Color.ORANGE)
                .setId(ID)
                .setName("Select3");

        var option3 = new SelectItem()
                .setColor(Color.RED)
                .setId(ID)
                .setName("Select1");

        return List.of(option1, option3);
    }

    private static CreatedBy createdByAllSet() {
        return (CreatedBy) new CreatedBy()
                .setUser(UserTestdata.peopleAllSet())
                .setId(ID)
                .setName("Created by");
    }

    private static Select selectAllSet() {
        return (Select) new Select()
                .setSelected(selectOptionsAllSet().getFirst())
                .setId(ID)
                .setName("Select");
    }

    private static RichText textAllSet() {
        return (RichText) new RichText()
                .setContent(List.of(RichTextTestdata.richTextTextAllSet()))
                .setId(ID)
                .setName("Text");
    }

    private static Formula formulaAllSet() {
        return (Formula) new Formula()
                .setType(FormulaType.STRING)
                .setStringValue("nein")
                .setId(ID)
                .setName("Formula");
    }

    private static Title titleAllSet() {
        return (Title) new Title()
                .setTitle(List.of(RichTextTestdata.richTextTextAllSet()))
                .setId(ID)
                .setName("Name");
    }
}
