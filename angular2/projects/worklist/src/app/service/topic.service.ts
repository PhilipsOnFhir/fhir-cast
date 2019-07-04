import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {observable, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private topicIds: string[];
  private baseUrl = "http://localhost:9444/api/fhircast/topic";

  constructor(  private http: HttpClient ) {
  }

  updateTopidIds(): Observable<any> {
    return new Observable<any>(
      observable => {

        this.http.get<string[]>( this.baseUrl  ).subscribe(
          next => { console.log( next);
              this.topicIds = next;
              observable.next();
              observable.complete();
          },
          error=> { console.log( error);         observable.error('Invalid URL');
          }
        );
      }
    );

  }

  createTopicId(): Observable<string> {
    return new Observable<string>(
      observable => {
        this.http.post<any>( this.baseUrl, "", {observe: 'response' as 'body'} ).subscribe(
          next => {
              let topicId = next.body.topicID;
              this.updateTopidIds().subscribe(
                next => {
                  observable.next(topicId);
                  observable.complete()
                },
                error => {}
              )
          },
        error=> { observable.error(error) }
        );

      }
    )
  }


  getTopicIds():string[] {
    return this.topicIds;
  }

  closeTopic( topicId:string ): Observable<string> {
    console.log("close topic "+topicId);
    console.log(this.baseUrl+topicId);
    return this.http.delete<any>( this.baseUrl+topicId);
  }
}
