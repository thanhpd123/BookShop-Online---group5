/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class BlogResponseDTO {
    private int BlogsID;
    private String BlogImg;
    private String Title;
    private String CategoryName;
    private String AuthorName;
    private boolean IsPublished;
    private String Content;
    private String BriefInformation;
            
    public BlogResponseDTO() {
    }

    public BlogResponseDTO(int BlogsID, String BlogImg, String Title, String CategoryName, String AuthorName, boolean IsPublished, String BriefInformation) {
        this.BlogsID = BlogsID;
        this.BlogImg = BlogImg;
        this.Title = Title;
        this.CategoryName = CategoryName;
        this.AuthorName = AuthorName;
        this.IsPublished = IsPublished;
        this.BriefInformation = BriefInformation;
    }

    public BlogResponseDTO(int BlogsID, String BlogImg, String Title, String CategoryName, String AuthorName, boolean IsPublished, String Content, String BriefInformation) {
        this.BlogsID = BlogsID;
        this.BlogImg = BlogImg;
        this.Title = Title;
        this.CategoryName = CategoryName;
        this.AuthorName = AuthorName;
        this.IsPublished = IsPublished;
        this.Content = Content;
        this.BriefInformation = BriefInformation;
    }

    public BlogResponseDTO(int BlogsID, String BlogImg, String Title, String AuthorName, boolean IsPublished, String BriefInformation) {
        this.BlogsID = BlogsID;
        this.BlogImg = BlogImg;
        this.Title = Title;
        this.AuthorName = AuthorName;
        this.IsPublished = IsPublished;
        this.BriefInformation = BriefInformation;
    }

    public BlogResponseDTO(int BlogsID, String BlogImg, String Title, String AuthorName, boolean IsPublished, String Content, String BriefInformation) {
        this.BlogsID = BlogsID;
        this.BlogImg = BlogImg;
        this.Title = Title;
        this.AuthorName = AuthorName;
        this.IsPublished = IsPublished;
        this.Content = Content;
        this.BriefInformation = BriefInformation;
    }

    public int getBlogsID() {
        return BlogsID;
    }

    public void setBlogsID(int BlogsID) {
        this.BlogsID = BlogsID;
    }

    public String getBlogImg() {
        return BlogImg;
    }

    public void setBlogImg(String BlogImg) {
        this.BlogImg = BlogImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    public boolean isIsPublished() {
        return IsPublished;
    }

    public void setIsPublished(boolean IsPublished) {
        this.IsPublished = IsPublished;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getBriefInformation() {
        return BriefInformation;
    }

    public void setBriefInformation(String BriefInformation) {
        this.BriefInformation = BriefInformation;
    }

    @Override
    public String toString() {
        return "BlogResponseDTO{" + "BlogsID=" + BlogsID + ", BlogImg=" + BlogImg + ", Title=" + Title + ", CategoryName=" + CategoryName + ", AuthorName=" + AuthorName + ", IsPublished=" + IsPublished + ", Content=" + Content + ", BriefInformation=" + BriefInformation + '}';
    }
   
}

