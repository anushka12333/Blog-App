import {myAxios, privateAxios} from './helper'

export const createPost =(postData)=>{
  //console.log("PostData "+postData);
  
  return privateAxios.post(`/user/${postData.userId}/category/${postData.categoryId}/posts`,postData).then((response) => response.data);
  
};
export const loadAllPosts=(pageNumber,pageSize)=>{
  return myAxios.get(`/posts?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=addeddate&sortDir=desc`).then((response) =>response.data);
}
export const loadPost=(postId)=>{
  return myAxios.get("/posts/"+postId).then(response=>response.data)

}

export const createComment=(comment,postId)=>{
  return privateAxios.post(`/post/${postId}/comments`,comment);

}
//upload post anner image
export const uploadPostImage=(image,postId)=>{
  let formData = new FormData();
  formData.append("image",image);
  return privateAxios.post(`/post/image/upload/${postId}`,formData,{
    headers:{
      'Content-Type':'multipart/form-data'
    }
  }).then((response)=>response.data);
}

//get category wisr post
export const loadPostCategoryWise=(categoryId)=>{
  return privateAxios.get(`/category/${categoryId}/posts`).then(res=>res.data);
}

export const loadPostUserWise=(userId)=>{
  return privateAxios.get(`user/${userId}/posts`).then(res=>res.data)
}

//delete post
export function ServicePostDelete(postId){
  return privateAxios.delete(`/posts/${postId}`).then(res=>res.data)
}