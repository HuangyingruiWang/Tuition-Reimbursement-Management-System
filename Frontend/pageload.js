user_id = sessionStorage.getItem("key");


function dataGenerator(col, index){
  let newTdTag = document.createElement("td");
  newTdTag.setAttribute("class","table-primary");
  newTdTag.innerHTML = col;
  document.getElementById(`eventsTableRow${index}`).appendChild(newTdTag);
}

function dataTag(data, index){
  let day = new Date(data.date * 1000);
  let newTrTag = document.createElement("tr");
  newTrTag.setAttribute("id",`eventsTableRow${index}`);
  if((data.date*1000-Date.now()) < (2*7*24*60*60*1000)){
    newTrTag.setAttribute("class",`table-danger`);
  }
  document.getElementById("allEventsTable").appendChild(newTrTag);
  dataGenerator(index, index);
  dataGenerator(day, index);
  dataGenerator(data.description, index);
  dataGenerator(data.cost, index);
  dataGenerator((data.cost*data.type.percetage), index);
  dataGenerator(data.format.cutoff_grade, index);
  dataGenerator(data.type.eventtype, index);
  dataGenerator(data.work_related_justification, index);
  dataGenerator(data.status.statusname, index);
}


function buttonGenerator(id, row, target, headerString, option1, option2){
  let newButtonTag = document.createElement("button");
  newButtonTag.setAttribute("id",`${id}`);
  newButtonTag.setAttribute("class","btn btn-primary");
  newButtonTag.setAttribute("data-bs-toggle","modal");
  newButtonTag.setAttribute("data-bs-target",`#${target}${id}`);
  newButtonTag.setAttribute("class","btn btn-primary");
  newButtonTag.innerHTML = "Option";
  document.getElementById(`${row}`).appendChild(newButtonTag);

  let div1 = document.createElement("div");
  div1.setAttribute("class", "modal fade");
  div1.setAttribute("id",`${target}${id}`);
  div1.setAttribute("tabindex","-1");
  div1.setAttribute("aria-labelledby",`h51${target}`);
  div1.setAttribute("aria-hidden","true");
  document.body.appendChild(div1);
  let div2 = document.createElement("div");
  div2.setAttribute("id",`div2${target}${id}`);
  div2.setAttribute("class","modal-dialog");
  document.getElementById(`${target}${id}`).appendChild(div2);
  let div3 = document.createElement("div");
  div3.setAttribute("id",`div3${target}${id}`);
  div3.setAttribute("class","modal-content");
  document.getElementById(`div2${target}${id}`).appendChild(div3);
  let div4 = document.createElement("div");
  div4.setAttribute("id",`div4${target}${id}`);
  div4.setAttribute("class","modal-header");
  document.getElementById(`div3${target}${id}`).appendChild(div4);
  let div5 = document.createElement("div");
  div5.setAttribute("id",`div5${target}${id}`);
  div5.setAttribute("class","modal-body");
  document.getElementById(`div3${target}${id}`).appendChild(div5);
  let div6 = document.createElement("div");
  div6.setAttribute("id",`div6${target}${id}`);
  div6.setAttribute("class","modal-footer");
  document.getElementById(`div3${target}${id}`).appendChild(div6);

  let h51 = document.createElement("h5");
  h51.setAttribute("id",`h51${target}${id}`);
  h51.setAttribute("class","modal-title");
  h51.innerHTML = `${headerString}`
  document.getElementById(`div4${target}${id}`).appendChild(h51);
  let button1 = document.createElement("button");
  button1.setAttribute("type","button");
  button1.setAttribute("class","btn-close");
  button1.setAttribute("data-bs-dismiss","modal");
  button1.setAttribute("aria-label","Close");
  document.getElementById(`div4${target}${id}`).appendChild(button1);

  let formBody = document.createElement("form");
  formBody.setAttribute("id",`form${target}${id}`);
  document.getElementById(`div5${target}${id}`).appendChild(formBody);

  let button2 = document.createElement("button");
  button2.setAttribute("id",`button2${target}${id}`);
  button2.setAttribute("type","button");
  button2.setAttribute("class","btn btn-secondary");
  button2.setAttribute("data-bs-dismiss","modal");
  button2.innerHTML = `${option1}`;
  document.getElementById(`div6${target}${id}`).appendChild(button2);
  let button3 = document.createElement("button");
  button3.setAttribute("id",`button3${target}${id}`);
  button3.setAttribute("type","button");
  button3.setAttribute("class","btn btn-primary");
  button3.setAttribute("data-bs-dismiss","modal");
  button3.innerHTML = `${option2}`;
  document.getElementById(`div6${target}${id}`).appendChild(button3);
}

async function updateRequest(obj, e_id, index){
  const url = `http://localhost:5432/users/${user_id}/events/${e_id}`;
  const httpResponse = await fetch(url,{
      method: "PUT",
      body: JSON.stringify(obj)
  });
  if(httpResponse.status === 200){
      alert("Submitted successfully!");
      document.getElementById(`button${index}`).setAttribute("disabled", "true");
  }else{
      alert("Submission Failed! Please ask technology development for help!\n Email: abc@abc.com");
  }
}

async function deleteRequest(e_id, index){
  const url = `http://localhost:5432/users/${user_id}/events/${e_id}`;
  const httpResponse = await fetch(url,{
    method: "DELETE",
  });
  if(httpResponse.status === 200){
      alert("Submitted successfully!");
      console.log(index);
      document.getElementById(`button${index}`).setAttribute("disabled", "true");
  }else{
      alert("Submission Failed! Please ask technology development for help!\n Email: abc@abc.com");
  }
}

function createOption(gradeName, elem){
  let newOption = document.createElement("option");
  newOption.setAttribute("value",`${gradeName}`);
  newOption.innerHTML = gradeName;
  document.getElementById(`${elem}`).appendChild(newOption);
}

function createRequest1Listener(index, event_id, code){
  document.getElementById(`requests1${index}`).addEventListener("click", () => {
    let obj ={
      status: {
        status_id: code
      }
    }; 
    updateRequest(obj,event_id, index);
  })
}
function createRequest2Listener(index, event_id, code){
  document.getElementById(`requests2${index}`).addEventListener("click", () => {
    let obj ={
      status: {
        status_id: code
      }
    }; 
    updateRequest(obj,event_id, index);
  })
}
function createRequest3Listener(index, event_id, code){
  document.getElementById(`requests3${index}`).addEventListener("click", () => {
    let obj ={
      status: {
        status_id: code
      }
    }; 
    updateRequest(obj,event_id,index);
  })
}

function createListener(target, index, event_id, code){
  document.getElementById(`button2${target}button${index}`).addEventListener("click", () => {
    const obj = {
      status: {
        status_id: 9
      }
    }
    updateRequest(obj,event_id, index);
  })
  
  document.getElementById(`button3${target}button${index}`).addEventListener("click", () => {
    let obj;
    if(code != 0){
      obj ={
        status: {
          status_id: code
        }
      }
    }else{
      obj = {
      }
    }
     
    updateRequest(obj,event_id,index);
  })
}

function createSelectLister(target, index, event_id){
  
  document.getElementById(`button3${target}button${index}`).addEventListener("click", () => {
    const Value = document.getElementById(`input${target}${index}`).value;
    const obj = {
      actual_grade: Value
    }
    updateRequest(obj,event_id,index);
  })
}

function createInputLister(target, index, event_id){
  
  document.getElementById(`button3${target}button${index}`).addEventListener("click", () => {
    const Value = document.getElementById(`input${target}${index}`).value;
    const obj = {
      cost: Value
    }
    updateRequest(obj,event_id,index);
  })
}

function extractEvents(events, supervisor, header){
  let index = 1;
  let owner;
  let newdivTag;
  let newdivTag2;
  let newinputTag;
  let newinputTag2;
  let newlabelTag;
  let newlabelTag2;
  let requests1Tag;  
  let requests2Tag;
  let requests3Tag;
  let obj;


  for(let eve of events){
    owner = eve.user_fk;
    if(user_id == owner.user_id){
      switch(eve.status.status_id){
        case 4:
          dataTag(eve,index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "updateActualGrade", "Update Your Grade or Presentation", "Close", "Update"); //Update grade or presentation
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1updateActualGradebutton${index}`)
          document.getElementById(`formupdateActualGradebutton${index}`).appendChild(newdivTag);
          newlabelTag = document.createElement("label");
          newlabelTag.setAttribute("for",`inputupdateActualGrade${index}`);
          newlabelTag.setAttribute("class","input-group-text");
          newlabelTag.innerHTML = "Actual Grade:";
          document.getElementById(`formupdateActualGradebutton${index}`).appendChild(newlabelTag);
          newinputTag = document.createElement("select");
          newinputTag.setAttribute("class","form-select");
          newinputTag.setAttribute("id",`inputupdateActualGrade${index}`);
          document.getElementById(`formupdateActualGradebutton${index}`).appendChild(newinputTag);
          switch(eve.format.grade_id){
            case 1:
            case 2:
              createOption("pass", `inputupdateActualGrade${index}`);
              createOption("fail", `inputupdateActualGrade${index}`);
              break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
              createOption("A", `inputupdateActualGrade${index}`);
              createOption("B", `inputupdateActualGrade${index}`);
              createOption("C", `inputupdateActualGrade${index}`);
              createOption("D", `inputupdateActualGrade${index}`);
              createOption("f", `inputupdateActualGrade${index}`); 
              break;
            case 8:
            case 9:
              createOption("pass presentation", `inputupdateActualGrade${index}`);
              createOption("fail presentation", `inputupdateActualGrade${index}`);
              break;
          }


          newdivTag2 = document.createElement("div");
          newdivTag2.setAttribute("class","mb-3");
          newdivTag2.setAttribute("id",`div2updateActualGradebutton${index}`)
          document.getElementById(`formupdateActualGradebutton${index}`).appendChild(newdivTag2);
          
          newlabelTag2 = document.createElement("label");
          newlabelTag2.setAttribute("for","updateActualGrade");
          newlabelTag2.setAttribute("class","col-form-label");
          newlabelTag2.innerHTML = "Attached Presentation:";
          document.getElementById(`formupdateActualGradebutton${index}`).appendChild(newlabelTag2);
          newinputTag2 = document.createElement("input");
          newinputTag2.setAttribute("type","file");
          newinputTag2.setAttribute("class","form-control");
          newinputTag2.setAttribute("id",`fileupdateActualGradebutton${index}`);
          document.getElementById(`formupdateActualGradebutton${index}`).appendChild(newinputTag2);

          createSelectLister("updateActualGrade",`${index}`, eve.event_id);
          index = index + 1;
          break;
        case 16:
          dataTag(eve,index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "option","Do you accept the exceeding available funds of this event?","Cancel this Request","Accept"); //Option Yes or Cancel
          document.getElementById(`button2optionbutton${index}`).addEventListener("click",() =>{
            deleteRequest(eve.event_id,index);
          })
          document.getElementById(`button3optionbutton${index}`).addEventListener("click", () => {
            obj = {
            }
            updateRequest(obj,eve.event_id,index);
          })

          index = index + 1;
          break;
        case 10:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "uploadAddFile","Update Additional Required Files","Close","Update"); //upload file
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1uploadAddFilebutton${index}`);
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newdivTag);
          
          newlabelTag = document.createElement("label");
          newlabelTag.setAttribute("for","uploadAddFile");
          newlabelTag.setAttribute("class","col-form-label");
          newlabelTag.innerHTML = "Attached Additional Document:";
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newlabelTag);
          newinputTag = document.createElement("input");
          newinputTag.setAttribute("type","file");
          newinputTag.setAttribute("class","form-control");
          newinputTag.setAttribute("id",`fileuploadAddFilebutton${index}`);
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newinputTag);

          createListener("uploadAddFile",index,eve.event_id,2);
          index = index + 1;
          break;
        case 11:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "uploadAddFile","Update Additional Required Files","Close","Update"); //upload file
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1uploadAddFilebutton${index}`);
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newdivTag);
          
          newlabelTag = document.createElement("label");
          newlabelTag.setAttribute("for","uploadAddFile");
          newlabelTag.setAttribute("class","col-form-label");
          newlabelTag.innerHTML = "Attached Additional Document:";
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newlabelTag);
          newinputTag = document.createElement("input");
          newinputTag.setAttribute("type","file");
          newinputTag.setAttribute("class","form-control");
          newinputTag.setAttribute("id",`fileuploadAddFilebutton${index}`);
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newinputTag);

          createListener("uploadAddFile",index,eve.event_id,3);
          index = index + 1;
          break;
        case 13:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "uploadAddFile","Update Additional Required Files","Close","Update"); //upload file
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1uploadAddFilebutton${index}`);
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newdivTag);
          
          newlabelTag = document.createElement("label");
          newlabelTag.setAttribute("for","uploadAddFile");
          newlabelTag.setAttribute("class","col-form-label");
          newlabelTag.innerHTML = "Attached Additional Document:";
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newlabelTag);
          newinputTag = document.createElement("input");
          newinputTag.setAttribute("type","file");
          newinputTag.setAttribute("class","form-control");
          newinputTag.setAttribute("id",`fileuploadAddFilebutton${index}`);
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newinputTag);

          createListener("uploadAddFile",index,eve.event_id,4);
          index = index + 1;
          break;
        case 1:
        case 2:
        case 3:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 17:
          dataTag(eve, index);
          index = index + 1;
          break;
      } 
      if(user_id == supervisor.user_id){
        switch(eve.status.status_id){
          case 12:
          case 14:
            dataTag(eve, index);
            buttonGenerator(`button${index}`, `eventsTableRow${index}`, "uploadAddFile","Update Additional Required Files","Close","Update"); //upload file
            newdivTag = document.createElement("div");
            newdivTag.setAttribute("class","mb-3");
            newdivTag.setAttribute("id",`div1uploadAddFilebutton${index}`)
            document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newdivTag);
            
            newlabelTag = document.createElement("label");
            newlabelTag.setAttribute("for","uploadAddFile");
            newlabelTag.setAttribute("class","col-form-label");
            newlabelTag.innerHTML = "Attached Additional Document:";
            document.getElementById(`divuploadAddFilebutton${index}`).appendChild(newlabelTag);
            newinputTag = document.createElement("input");
            newinputTag.setAttribute("type","file");
            newinputTag.setAttribute("class","form-control");
            newinputTag.setAttribute("id",`fileuploadAddFilebutton${index}`);
            document.getElementById(`divuploadAddFilebutton${index}`).appendChild(newinputTag);
  
            createListener("uploadAddFile",index,eve.event_id, 4);
            index = index + 1;
            break;
        }
        if(user_id == header.user_id){
          if(eve.status.status_id == 15){
            dataTag(eve, index);
            buttonGenerator(`button${index}`, `eventsTableRow${index}`, "uploadAddFile","Update Additional Required Files","Close","Update"); //upload file
            newdivTag = document.createElement("div");
            newdivTag.setAttribute("class","mb-3");
            newdivTag.setAttribute("id",`div1uploadAddFilebutton${index}`)
            document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newdivTag);
            
            newlabelTag2 = document.createElement("label");
            newlabelTag2.setAttribute("for","uploadAddFile");
            newlabelTag2.setAttribute("class","col-form-label");
            newlabelTag2.innerHTML = "Attached Additional Document:";
            console.log(index);
            document.getElementById(`div1uploadAddFilebutton${index}`).appendChild(newlabelTag2);
            newinputTag = document.createElement("input");
            newinputTag.setAttribute("type","file");
            newinputTag.setAttribute("class","form-control");
            newinputTag.setAttribute("id",`fileuploadAddFilebutton${index}`);
            document.getElementById(`div1uploadAddFilebutton${index}`).appendChild(newinputTag);
  
            createListener("uploadAddFile",index,eve.event_id, 4);
            index = index + 1;
            break;
          }
        }
      }
      if(user_id == header.user_id){
        if(eve.status.status_id == 15){
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "uploadAddFile","Update Additional Required Files","Close","Update"); //upload file
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1uploadAddFilebutton${index}`)
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newdivTag);
          
          newlabelTag = document.createElement("label");
          newlabelTag.setAttribute("for","uploadAddFile");
          newlabelTag.setAttribute("class","col-form-label");
          newlabelTag.innerHTML = "Attached Additional Document:";
          document.getElementById(`div1uploadAddFilebutton${index}`).appendChild(newlabelTag);
          newinputTag = document.createElement("input");
          newinputTag.setAttribute("type","file");
          newinputTag.setAttribute("class","form-control");
          newinputTag.setAttribute("id",`fileuploadAddFilebutton${index}`);
          document.getElementById(`div1uploadAddFilebutton${index}`).appendChild(newinputTag);

          createListener("uploadAddFile",index,eve.event_id, 4);
          index = index + 1;
          break;
        }
        switch(eve.status.status_id){
          case 12:
          case 14:
          dataTag(eve, index);
          index = index + 1;
          break;
        }
      }
    }else{
      switch(eve.status.status_id){
        case 1:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "firstApproval","Approval or Deny this event?","Deny","Approval"); //Option approval or deny
          requests1Tag = document.createElement("button");
          requests1Tag.setAttribute("id",`requests1${index}`)
          requests1Tag.setAttribute("type","button");
          requests1Tag.setAttribute("class","btn btn-primary");
          requests1Tag.setAttribute("data-bs-dismiss","modal");
          requests1Tag.innerHTML = "Requests employee to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests1Tag);
          createRequest1Listener(index,eve.event_id,10);
          requests2Tag = document.createElement("button");
          requests2Tag.setAttribute("id",`requests2${index}`)
          requests2Tag.setAttribute("type","button");
          requests2Tag.setAttribute("class","btn btn-primary");
          requests2Tag.setAttribute("data-bs-dismiss","modal");
          requests2Tag.setAttribute("disabled","true");
          requests2Tag.innerHTML = "Requests direct supervisor to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests2Tag);
          requests3Tag = document.createElement("button");
          requests3Tag.setAttribute("id",`requests3${index}`)
          requests3Tag.setAttribute("type","button");
          requests3Tag.setAttribute("class","btn btn-primary");
          requests3Tag.setAttribute("data-bs-dismiss","modal");
          requests3Tag.setAttribute("disabled","true");
          requests3Tag.innerHTML = "Requests department header to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests3Tag);
          
          createListener("firstApproval",index,eve.event_id,0);
          index = index + 1;
          break;
        case 2:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "firstApproval","Approval or Deny this event?","Deny","Approval"); //Option approval or deny
          requests1Tag = document.createElement("button");
          requests1Tag.setAttribute("id",`requests1${index}`)
          requests1Tag.setAttribute("type","button");
          requests1Tag.setAttribute("class","btn btn-primary");
          requests1Tag.setAttribute("data-bs-dismiss","modal");
          requests1Tag.innerHTML = "Requests employee to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests1Tag);
          createRequest1Listener(index,eve.event_id,11);
          requests2Tag = document.createElement("button");
          requests2Tag.setAttribute("id",`requests2${index}`)
          requests2Tag.setAttribute("type","button");
          requests2Tag.setAttribute("class","btn btn-primary");
          requests2Tag.setAttribute("data-bs-dismiss","modal");
          requests2Tag.innerHTML = "Requests direct supervisor to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests2Tag);
          createRequest2Listener(index,eve.event_id,12);
          requests3Tag = document.createElement("button");
          requests3Tag.setAttribute("id",`requests3${index}`)
          requests3Tag.setAttribute("type","button");
          requests3Tag.setAttribute("class","btn btn-primary");
          requests3Tag.setAttribute("data-bs-dismiss","modal");
          requests3Tag.setAttribute("disabled","true");
          requests3Tag.innerHTML = "Requests department header to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests3Tag);
          createListener("firstApproval",index,eve.event_id,0);
          index = index + 1;
          break;
        case 3:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "firstApproval","Approval or Deny this event?","Deny","Approval"); //Option approval or deny
          requests1Tag = document.createElement("button");
          requests1Tag.setAttribute("id",`requests1${index}`)
          requests1Tag.setAttribute("type","button");
          requests1Tag.setAttribute("class","btn btn-primary");
          requests1Tag.setAttribute("data-bs-dismiss","modal");
          requests1Tag.innerHTML = "Requests employee to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests1Tag);
          createRequest1Listener(index,eve.event_id,13);
          requests2Tag = document.createElement("button");
          requests2Tag.setAttribute("id",`requests2${index}`)
          requests2Tag.setAttribute("type","button");
          requests2Tag.setAttribute("class","btn btn-primary");
          requests2Tag.setAttribute("data-bs-dismiss","modal");
          requests2Tag.innerHTML = "Requests direct supervisor to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests2Tag);
          createRequest2Listener(index,eve.event_id,14);
          requests3Tag = document.createElement("button");
          requests3Tag.setAttribute("id",`requests3${index}`)
          requests3Tag.setAttribute("type","button");
          requests3Tag.setAttribute("class","btn btn-primary");
          requests3Tag.setAttribute("data-bs-dismiss","modal");
          requests3Tag.innerHTML = "Requests department header to provided additional document";
          document.getElementById(`formfirstApprovalbutton${index}`).appendChild(requests3Tag);
          createRequest3Listener(index,eve.event_id,15);
          createListener("firstApproval",index,eve.event_id,0);
          index = index + 1;
          break;
        case 6:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "supervisorApprovalPresentation","The presentation was satisfactory or not?","Deny","Confirm"); //1Option approval or deny
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1supervisorApprovalPresentationbutton${index}`)
          newdivTag.innerHTML = "Presentatation: unknown";
          document.getElementById(`formsupervisorApprovalPresentationbutton${index}`).appendChild(newdivTag);

          createListener("supervisorApprovalPresentation",index,eve.event_id,0);
          index = index + 1;
          break;
  
        case 5:
        case 7:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "finalApproval","Approval or Deny this event?","Deny","Approval"); //Option approval or deny
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1finalApprovalbutton${index}`)
          if(eve.actual_grade != null){
            newdivTag.innerHTML = "Actual Grade: " + eve.actual_grade;
          }else{
            newdivTag.innerHTML = "Actual Grade: unknown";
          }
          document.getElementById(`formfinalApprovalbutton${index}`).appendChild(newdivTag);
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div2finalApprovalbutton${index}`)
          newdivTag.innerHTML = "Presentatation: unknown";
          document.getElementById(`formfinalApprovalbutton${index}`).appendChild(newdivTag);

          createListener("finalApproval",index,eve.event_id, 0);
          index = index + 1;
          break;
        
        case 12:
        case 14:
        case 15:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "uploadAddFile","Update Additional Required Files","Close","Update"); //upload file
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1uploadAddFilebutton${index}`)
          document.getElementById(`formuploadAddFilebutton${index}`).appendChild(newdivTag);
          
          newlabelTag = document.createElement("label");
          newlabelTag.setAttribute("for","uploadAddFile");
          newlabelTag.setAttribute("class","col-form-label");
          newlabelTag.innerHTML = "Attached Additional Document:";
          document.getElementById(`divuploadAddFilebutton${index}`).appendChild(newlabelTag);
          newinputTag = document.createElement("input");
          newinputTag.setAttribute("type","file");
          newinputTag.setAttribute("class","form-control");
          newinputTag.setAttribute("id",`fileuploadAddFilebutton${index}`);
          document.getElementById(`divuploadAddFilebutton${index}`).appendChild(newinputTag);

          createListener("uploadAddFile",index,eve.event_id, 4);
          index = index + 1;
          break;
        
        case 8:
        case 9:
          dataTag(eve, index);
          index = index + 1;
          break;
        case 17:
          dataTag(eve, index);
          buttonGenerator(`button${index}`, `eventsTableRow${index}`, "changeAmount","What Reimbursement Amount do you want to change?","Close","Update"); //upload file
          newdivTag = document.createElement("div");
          newdivTag.setAttribute("class","mb-3");
          newdivTag.setAttribute("id",`div1changeAmountbutton${index}`)
          document.getElementById(`formchangeAmountbutton${index}`).appendChild(newdivTag);

          newlabelTag = document.createElement("label");
          newlabelTag.setAttribute("for","changeAmount");
          newlabelTag.setAttribute("class","col-form-label");
          newlabelTag.innerHTML = "Change Reimbursement Amount:";
          document.getElementById(`div1changeAmountbutton${index}`).appendChild(newlabelTag);
          newinputTag = document.createElement("input");
          newinputTag.setAttribute("type","number");
          newinputTag.setAttribute("class","form-control");
          newinputTag.setAttribute("id",`inputchangeAmount${index}`);
          newinputTag.setAttribute("min","0");
          const current = eve.cost*eve.type.percetage;
          newinputTag.setAttribute("value",`${current}`);
          document.getElementById(`div1changeAmountbutton${index}`).appendChild(newinputTag);

          createInputLister("changeAmount", `${index}`, eve.event_id);
          index = index + 1;
          break;    
      }
    }
  }
}

async function getAllEvents(){
    const eventurl = `http://localhost:5432/users/${user_id}/events`;
    const eventHttpResponse = await fetch(eventurl);
    const eventBody = await eventHttpResponse.json();
    const runnerurl = `http://localhost:5432/users/${user_id}`;
    const runnerHttpResponse = await fetch(runnerurl);
    const runnerBody = await runnerHttpResponse.json();
    const superurl = `http://localhost:5432/supervisors/${user_id}`;
    const superHttpResponse = await fetch(superurl);
    const superBody = await superHttpResponse.json();
    const headerurl = `http://localhost:5432/heads/${runnerBody.department.department_id}`;
    const headerHttpResponse = await fetch(headerurl);
    const headerBody = await headerHttpResponse.json();
    
    console.log(eventBody);
    extractEvents(eventBody, superBody, headerBody);
}




    function openNewWindow(url){
        open(url);
    }
    
    function getTime(){
      return Math.floor(new Date().getTime()/1000.0);
    }

    document.getElementById("newSubmitButton").addEventListener("click", () => {
      openNewWindow("UserForm.html")
    })

    document.getElementById("logoutButton").addEventListener("click", () => {
      sessionStorage.removeItem('key');
      location.href= "LogIn.html";
    })
