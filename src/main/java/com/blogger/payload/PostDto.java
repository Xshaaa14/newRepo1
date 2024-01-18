package com.blogger.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min=2, message = "Title should be at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min=4, message = "Description should be at least 4 characters")
    private String description;

    @NotEmpty
    @Size(min=4, message = "Content should be at least 4 characters")
    private String content;

	public Object getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(Object id2) {
		// TODO Auto-generated method stub
		
	}

	public void setTitle(Object title2) {
		// TODO Auto-generated method stub
		
	}

	public void setContent(Object content2) {
		// TODO Auto-generated method stub
		
	}

	public void setDescription(Object description2) {
		// TODO Auto-generated method stub
		
	}


}
//validation should happen in controller, if data is correct after checking then the data should go to service