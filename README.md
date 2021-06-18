# di-ipv-gpg45-engine

The GPG45 scoring/calculation engine.

## Running locally

Build or run via gradle by either executing `bootRun` or `build` tasks.

## Paths

| Path | Description |
| - | - |
| POST `/calculate` | Post an identity verification bundle to be scored and matched to a profile. |
| GET `/health` | Health status information. |

## How it works

#### Request
The service expects a `POST` request to `/calculate` path, it expects the request body to look
as defined in the [sample gpg45 engine json input](/docs/sample-gpg45-engine-input.json).

#### Evidence Scoring
For each provided evidence, the service will score the evidence strength and validity based
on some set parameters.

You can find all available [evidence types here](/src/main/java/uk/gov/di/gpg45engine/domain/data/EvidenceType.java).

#### Matching to a profile
The scored evidence bundle is then passed to the profile matching service, this attempts to match
the scored evidences to a profile, see [all defined JSON profiles](/src/main/resources/profiles).

#### Response
The response of the GPG45 engine is the scored identity bundle sent back along with the attached
identity profile.
