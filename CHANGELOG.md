# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Your new feature here.

## [1.0.0] - 2025-09-23

### Added
- Initial project setup with a multi-module Gradle build.
- Benchmark runner (`runner` module) to execute and compare all concurrency models.
- `core` module with shared benchmark logic (CPU, I/O tasks).
- `loom` module for Virtual Threads implementation.
- `structured` module for Structured Concurrency implementation.
- `completablefuture` module for `CompletableFuture` implementation.
- `rxjava` module for RxJava implementation.
- `coroutines` module for Kotlin Coroutines implementation.
- Detailed `README.md` files for the root project and each individual module.
- Project documentation: `LICENSE`, `CODE_OF_CONDUCT.md`, and `CONTRIBUTING.md`.
