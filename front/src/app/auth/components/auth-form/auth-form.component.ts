import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, map, of, startWith, tap } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { passwordValidator } from 'src/app/shared/Validators/password.validator';

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.scss']
})
export class AuthFormComponent implements OnInit {

  @Input()
  public isLogin: boolean = false;

  public isLoading: boolean = false;
  public formInError: boolean = false;
  public emailCtrl!: FormControl;
  public passwordCtrl!: FormControl;
  public authForm!: FormGroup;
  public passwordError!: Observable<string>;

  public constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }

  public ngOnInit(): void {
    this.emailCtrl = this.fb.control("", [Validators.required, Validators.email]);
    this.passwordCtrl = this.fb.control("", [Validators.required, passwordValidator()]);

    if (this.isLogin) {
      this.authForm = this.fb.group(
        {
          email: this.emailCtrl,
          password: ["", Validators.required]
        }
      );
    } else {
      this.authForm = this.fb.group(
        {
          username: ["", Validators.required],
          email: this.emailCtrl,
          password: this.passwordCtrl
        },
        {
          updateOn: "blur"
        }
      );

      this.passwordError = this.passwordCtrl.valueChanges.pipe(
        startWith("Le champ est requis."),
        map(() => {
          if (this.passwordCtrl.errors?.["required"]) {
            return "Le champ est requis.";
          }
          return this.passwordCtrl.errors?.["password"];
        })
      );
    }
  }

  public onSubmitForm(): void {
    this.isLoading = true;
    const authServiceMethodToCall = this.isLogin ? this.authService.loginUser : this.authService.registerUser;
    authServiceMethodToCall(this.authForm.value).subscribe({
      next: (isOk) => {
        if (isOk) {
          this.router.navigate(["/post"]);
        }
        this.formInError = !isOk;
        this.isLoading = false;
      },
      error: () => {
        this.formInError = true;
        this.isLoading = false;
      }
    })
  }

  public onGoBack(): void {
    history.back();
  }

}
