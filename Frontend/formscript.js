let user_id = sessionStorage.getItem("key");
const elementdate = document.getElementById("inputDate");


function nextweek(){
    var today = new Date();
    var nextweek = new Date(today.getFullYear(), today.getMonth(), today.getDate()+7);
    return nextweek.toJSON().slice(0,10);
}

const day7 = nextweek();
console.log(day7)
elementdate.setAttribute("min", `${day7}`);

function change1(){
    let select1 = document.getElementById("inputGradingFormatSelect");
    let select2 = document.getElementById("inputCutoffGradeSelect");
    select2.options.length = 0;
    if(select1.selectedIndex == 1){//Pass/Fail
            select2.options.add(new Option("Pass", 1, false, true));
            select2.options.add(new Option("Fail", 2)); 
    }
    
    if(select1.selectedIndex == 2){//Letter Grade
            select2.options.add(new Option("A", 3));
            select2.options.add(new Option("B", 4));
            select2.options.add(new Option("C", 5, false, true));
            select2.options.add(new Option("D", 5));
            select2.options.add(new Option("F", 6));
    }
    if(select1.selectedIndex == 3){//Presentation
        select2.options.add(new Option("Pass", 7, false, true));
        select2.options.add(new Option("Fail", 8));
    }
    select2.disabled = false;
}
// function change2(){
//     let select2 = document.getElementById("inputCutoffGradeSelect");
//     let select3 = document.getElementById("inputGradeSelect");
//     select3.options.length = 0;
//     if(select2.selectedIndex === 2){
//         if(select2.selectedIndex == 0){//Pass
//             select3.options.add(new Option("Pass", "1", false, true));
//             select3.options.add(new Option("Fail", "0")); 
//         }
        
//         if(select2.selectedIndex == 1){//Letter Grade
//             select3.options.add(new Option("Pass", "1"));
//             select3.options.add(new Option("Fail", "1", false, true)); 
//         }
//     }else if(select2.selectedIndex === 3){
//         if(select2.selectedIndex == 0){//Pass
//             select3.options.add(new Option("A", "1", false, true));
//             select3.options.add(new Option("B", "0"));
//             select3.options.add(new Option("C", "0"));
//             select3.options.add(new Option("F", "0"));
//         }
        
//         if(select2.selectedIndex == 1){//Letter Grade
//             select3.options.add(new Option("A", "1"));
//             select3.options.add(new Option("B", "1", false, true));
//             select3.options.add(new Option("C", "0"));
//             select2.options.add(new Option("D", "0"));
//             select3.options.add(new Option("F", "0"));
//         }

//         if(select2.selectedIndex == 2){//Letter Grade
//             select3.options.add(new Option("A", "1"));
//             select3.options.add(new Option("B", "1"));
//             select3.options.add(new Option("C", "1", false, true));
//             select2.options.add(new Option("D", "0"));
//             select3.options.add(new Option("F", "0"));
//         }

//         if(select2.selectedIndex == 3){//Letter Grade
//             select3.options.add(new Option("A", "1"));
//             select3.options.add(new Option("B", "1"));
//             select3.options.add(new Option("C", "1"));
//             select2.options.add(new Option("D", "1", false, true));
//             select3.options.add(new Option("F", "0"));
//         }
//         if(select2.selectedIndex == 3){//Letter Grade
//             select3.options.add(new Option("A", "1"));
//             select3.options.add(new Option("B", "1"));
//             select3.options.add(new Option("C", "1"));
//             select2.options.add(new Option("D", "1"));
//             select3.options.add(new Option("F", "1", false, true));
//         }
//     }
    
 
// }




document.getElementById("submitButton").addEventListener("click", () => {
    const eventTypeValue = document.getElementById("inputEventTypeSelect").value;
    const costValue = document.getElementById("inputCost").value;
    const dateValue = document.getElementById("inputDate").value;
    const locationValue = document.getElementById("inputLocation").value;
    const emailValue = document.getElementById("inputEmail").value;
    const descriptionValue = document.getElementById("inputDescription").value;
    const gradingFormatValue = document.getElementById("inputCutoffGradeSelect").value;

    const thisDate = new Date(dateValue);
    const obj = {
        user_fk: { 
            user_id: user_id
        },
        type: { 
            type_id: eventTypeValue
        },
        cost: costValue,
        date: thisDate.getTime()/1000.0,
        location: locationValue,
        email_address: emailValue,
        description: descriptionValue,
        format: {
            grade_id: gradingFormatValue
        }
    }
    sendRequest(obj);
})

async function sendRequest(o){
    const url = `http://localhost:5432/users/${user_id}/events`;
    const httpResponse = await fetch(url,{
        method: "POST",
        body: JSON.stringify(o)
    });
    if(httpResponse.status === 200){
        alert("Submitted successfully!");
        close();
    }else{
        alert("Submission Failed! Maybe you have reached the reimbursement quota! Please ask technology development for help!\n Email: abc@abc.com");
    }

    console.log(obj);
}


