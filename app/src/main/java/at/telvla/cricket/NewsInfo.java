package at.telvla.cricket;

public class NewsInfo {

    private String Id;
    private String Title;
    private String Abstr;
    private String Description;
    private String Date;
    private String Img;
    private String Img_description;
    private String Name_razdel;
    private String Link_page;
    private String CountView;

    NewsInfo (String Id, String Title, String Abstr, String Description, String Date, String Img, String Img_description, String Name_razdel, String Link_page, String CountView) {
        this.Id = Id;
        this.Title = Title;
        this.Abstr = Abstr;
        this.Description = Description;
        this.Date = Date;
        this.CountView = CountView;
        this.Img = Img;
        this.Img_description = Img_description;
        this.Abstr = Name_razdel;
        this.Link_page = Link_page;
    }

    public String getCountView() {
        return CountView;
    }

    public void setCountView(String CountView) {CountView = CountView;}

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAbstr() {
        return Abstr;
    }

    public void setAbstr(String abstr) {
        Abstr = abstr;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getImg_description() {
        return Img_description;
    }

    public void setImg_description(String img_description) {
        Img_description = img_description;
    }

    public String getName_razdel() {
        return Name_razdel;
    }

    public void setName_razdel(String name_razdel) {
        Name_razdel = name_razdel;
    }

    public String getLink_page() {
        return Link_page;
    }

    public void setLink_page(String link_page) {
        Link_page = link_page;
    }

}
