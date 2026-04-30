{ pkgs, ... }:

# Use a let block to define a variable for the Android SDK to avoid repetition.
let
  # The androidsdk package provides the basic SDK "bundle".
  androidsdk = pkgs.androidsdk;
in
{
  # Use the unstable channel to get newer versions of Android tools.
  channel = "unstable";

  # A list of packages to install from the specified channel.
  packages = [
    pkgs.jdk
    pkgs.gradle
    # Add platform-tools and build-tools to your environment.
    androidsdk
    (androidsdk.override {
      sdkPackages = [ "platform-tools" "build-tools" ];
    })
  ];

  # A set of environment variables to define within the workspace.
  env = {
    # Set environment variables for the Android SDK.
    ANDROID_HOME = "${androidsdk}/libexec/android-sdk";
    # Add platform-tools and build-tools to your PATH.
    PATH = [
      "${androidsdk}/libexec/android-sdk/platform-tools"
      "${androidsdk}/libexec/android-sdk/build-tools"
      "$PATH"
    ];
  };

  # Project IDX specific configurations.
  idx = {
    # A list of VS Code extensions to install from the Open VSX Registry.
    extensions = [
      "google.gemini-cli-vscode-ide-companion"
      "fwcd.kotlin"
      "redhat.java"
    ];

    # Workspace lifecycle hooks.
    workspace = {
      # Runs when a workspace is first created.
      onCreate = {
        # Grant execute permissions to the gradlew wrapper script.
        make-gradelw-executable = "chmod +x ./gradlew";
      };
    };
  };
}
